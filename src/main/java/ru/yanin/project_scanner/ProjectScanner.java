package ru.yanin.project_scanner;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * ProjectScanner - a utility for scanning project structure and consolidating
 * all source files into a single text document.
 * This is useful for code analysis, documentation, or providing the entire project as a single text file.
 * </p>
 *
 * <p><b>Usage:</b></p>
 * <pre>
 * java ProjectScanner.java [project_path] [output_file]
 * </pre>
 *
 * <p><b>Examples:</b></p>
 * <pre>
 * // Scan current directory, output to project_code.txt
 * java ProjectScanner
 *
 * // Scan specified directory
 * java ProjectScanner /home/user/myproject
 *
 * // Scan with custom output file
 * java ProjectScanner /home/user/myproject project_snapshot.txt
 * </pre>
 *
 * @author Yanin Vyacheslav
 * @version 1.0
 * @since 2026
 */
@SuppressWarnings("CallToPrintStackTrace")
public class ProjectScanner {

    /**
     * Entry point of the application.
     *
     * @param args Command-line arguments:
     *             <ul>
     *             <li>args[0] - (mandatory) Name of Language configuration</li>
     *             <li>args[1] - (optional) Path to the project root directory.
     *             Defaults to current directory (".")</li>
     *             <li>args[2] - (optional) Name of the output file.
     *             Defaults to "project_code.txt"</li>
     *             </ul>
     */
    public static void main(String[] args) {
        LanguageConfig languageConfig;
        try {
            languageConfig = LanguageConfig.fromLanguageName(args.length > 0 ? args[0] : " ");
        } catch (Exception e) {
            System.err.println("Language detection error: " + e.getMessage());
            System.err.println("Supported languages : " + Arrays.toString(LanguageConfig.values()));
            return;
        }
        List<String> includedExtensions = languageConfig.getIncludedExtensions();
        List<String> excludedDirs = languageConfig.getExcludedDirs();
        String projectRoot = args.length > 1 ? args[1] : ".";
        String outputFile = args.length > 2 ? args[2] : "project_code.txt";

        try {
            scanProject(projectRoot, outputFile, excludedDirs, includedExtensions);
            System.out.println("Project scanned successfully! Output: " + outputFile);
        } catch (IOException e) {
            System.err.println("Error scanning project: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Scans the project directory and generates a consolidated output file.
     *
     * @param rootPath           The root directory path of the project to scan
     * @param outputFile         The name of the file where the consolidated output will be written
     * @param excludedDirs       List of directory names that will be excluded from the scan
     * @param includedExtensions List of file extensions that will be included in the scan
     */
    private static void scanProject(String rootPath, String outputFile,
                                    List<String> excludedDirs, List<String> includedExtensions) throws IOException {
        Path root = Paths.get(rootPath).toAbsolutePath();
        List<Path> files = new ArrayList<>();
        Files.walkFileTree(root, getFileVisitor(files, excludedDirs, includedExtensions));

        // Sort files by path depth (shorter paths first), then by path name
        files.sort(Comparator.comparingInt(Path::getNameCount).thenComparing(p -> p));

        try (var writer = new PrintWriter(new FileWriter(outputFile))) {
            writer.println("PROJECT STRUCTURE:");
            writer.println("==================");
            for (Path file : files) {
                writer.println(root.relativize(file));
            }

            writer.println("\n\nSOURCE CODE:");
            writer.println("============");

            for (Path file : files) {
                writer.println("\n" + "=".repeat(80));
                writer.println("FILE: " + root.relativize(file));
                writer.println("=".repeat(80));
                try {
                    List<String> lines = Files.readAllLines(file);
                    for (String line : lines) {
                        writer.println(line);
                    }
                } catch (IOException e) {
                    writer.println("ERROR READING FILE: " + e.getMessage());
                }
            }
        }
    }

    private static FileVisitor<Path> getFileVisitor(List<Path> files,
                                                    List<String> excludedDirs, List<String> includedExtensions) {
        return new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                String dirName = dir.getFileName().toString();
                if (excludedDirs.contains(dirName)) {
                    return FileVisitResult.SKIP_SUBTREE;
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                if (isIncludedFile(file, includedExtensions)) {
                    files.add(file);
                }
                return FileVisitResult.CONTINUE;
            }
        };
    }

    private static boolean isIncludedFile(Path file, List<String> includedExtensions) {
        String fileName = file.getFileName().toString();
        return includedExtensions.stream()
                .anyMatch(ext -> fileName.toLowerCase().endsWith(ext));
    }
}