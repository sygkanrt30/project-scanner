package ru.yanin.project_scanner;

import java.util.List;

public enum LanguageConfig {
    JAVA(
            List.of(".java", ".gradle", ".kt", ".kts", ".xml", ".yml", ".yaml", ".properties", ".json", ".md", ".txt"),
            List.of(".git", "build", "target", "out", "bin", ".idea", "node_modules", ".gradle", ".vscode")
    ),

    CSHARP(
            List.of(".cs", ".csproj", ".sln", ".config", ".xml", ".yml", ".yaml", ".json", ".md", ".txt", ".resx"),
            List.of(".git", "bin", "obj", "packages", "out", ".vs", ".vscode", ".idea", "node_modules")
    ),

    PYTHON(
            List.of(".py", ".pyi", ".pyx", ".ipynb", ".yml", ".yaml", ".json", ".ini", ".cfg", ".md", ".txt", ".toml", ".sh"),
            List.of(".git", "__pycache__", "venv", "env", ".venv", ".env", "build", "dist", "*.egg-info", ".pytest_cache", ".mypy_cache", ".vscode", ".idea")
    ),

    RUBY(
            List.of(".rb", ".erb", ".rake", ".gemfile", ".yml", ".yaml", ".json", ".md", ".txt", ".ru", ".thor"),
            List.of(".git", "vendor", ".bundle", "tmp", "log", "coverage", ".vscode", ".idea")
    ),

    GOLANG(
            List.of(".go", ".mod", ".sum", ".yml", ".yaml", ".json", ".md", ".txt", ".sh", ".proto"),
            List.of(".git", "vendor", "bin", "out", "build", ".vscode", ".idea")
    ),

    JAVASCRIPT(
            List.of(".js", ".jsx", ".ts", ".tsx", ".json", ".yml", ".yaml", ".md", ".txt", ".html", ".css", ".scss", ".less"),
            List.of(".git", "node_modules", "dist", "build", "coverage", ".vscode", ".idea", "out", "bin")
    ),

    PHP(
            List.of(".php", ".php7", ".phar", ".module", ".inc", ".yml", ".yaml", ".json", ".md", ".txt", ".xml", ".htaccess"),
            List.of(".git", "vendor", "node_modules", "bin", "var", "cache", "logs", ".vscode", ".idea")
    ),

    RUST(
            List.of(".rs", ".toml", ".lock", ".yml", ".yaml", ".json", ".md", ".txt"),
            List.of(".git", "target", "debug", "release", ".vscode", ".idea", "node_modules")
    ),

    SWIFT(
            List.of(".swift", ".xcodeproj", ".xcworkspace", ".plist", ".yml", ".yaml", ".json", ".md", ".txt", ".strings"),
            List.of(".git", "build", "DerivedData", "Pods", ".vscode", ".idea", "node_modules")
    ),

    KOTLIN(
            List.of(".kt", ".kts", ".java", ".gradle", ".xml", ".yml", ".yaml", ".properties", ".json", ".md", ".txt"),
            List.of(".git", "build", "target", "out", "bin", ".idea", ".gradle", ".settings", ".vscode")
    ),

    SCALA(
            List.of(".scala", ".sbt", ".sc", ".java", ".gradle", ".xml", ".yml", ".yaml", ".properties", ".json", ".md", ".txt"),
            List.of(".git", "target", "project", "bin", "out", ".vscode", ".idea", ".settings", ".metals")
    ),

    PERL(
            List.of(".pl", ".pm", ".t", ".pod", ".yml", ".yaml", ".json", ".md", ".txt", ".cgi"),
            List.of(".git", "_build", "blib", "cover_db", ".vscode", ".idea")
    ),

    SHELL(
            List.of(".sh", ".bash", ".zsh", ".fish", ".ksh", ".profile", ".rc", ".yml", ".yaml", ".json", ".md", ".txt"),
            List.of(".git", ".vscode", ".idea", "node_modules")
    ),

    SQL(
            List.of(".sql", ".plsql", ".pkb", ".pks", ".sqlplus", ".yml", ".yaml", ".json", ".md", ".txt"),
            List.of(".git", ".vscode", ".idea")
    ),

    DART(
            List.of(".dart", ".yaml", ".json", ".md", ".txt", ".html", ".css", ".js"),
            List.of(".git", "build", "dist", "coverage", ".dart_tool", "node_modules", ".vscode", ".idea")
    ),

    R(
            List.of(".r", ".rmd", ".rdata", ".rds", ".yml", ".yaml", ".json", ".md", ".txt", ".rnw"),
            List.of(".git", "renv", ".Rproj.user", ".vscode", ".idea")
    );

    private final List<String> includedExtensions;
    private final List<String> excludedDirs;

    LanguageConfig(List<String> includedExtensions, List<String> excludedDirs) {
        this.includedExtensions = includedExtensions;
        this.excludedDirs = excludedDirs;
    }

    public List<String> getIncludedExtensions() {
        return includedExtensions;
    }

    public List<String> getExcludedDirs() {
        return excludedDirs;
    }

    public static LanguageConfig fromLanguageName(String language) {
        return valueOf(language.toUpperCase());
    }
}
