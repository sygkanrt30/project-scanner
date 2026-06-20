# Project Scanner

[![Java Version](https://img.shields.io/badge/Java-21+-blue.svg)](https://adoptium.net/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-red.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)
[![Release](https://img.shields.io/badge/Release-v1.0.0-orange.svg)](https://github.com/sygkanrt30/ProjectScanner/releases)

A utility that scans your project and consolidates all source code into a single text file — **perfect for providing context to Large Language Models (LLMs) like ChatGPT, Claude, or GitHub Copilot**.

Instead of manually copying files one by one, this tool generates a structured text document containing your entire project's code, making it easy to paste into an LLM for code review, analysis, documentation generation, or bug hunting.

---

##  Why This Tool?

When working with LLMs, you often need to provide your entire codebase as context. This tool:

-  **Collects all source files** into one organized text file
-  **Preserves file structure** with clear separators
-  **Excludes unnecessary files** (build artifacts, .git, etc.)
-  **Saves hours** of manual copy-pasting
-  **Creates LLM-ready context** in seconds

---

##  Installation Options

### Option 1: Download EXE (Windows - Recommended)

Download the latest version of [**ProjectScanner.exe**](https://github.com/sygkanrt30/project-scanner/releases/download/exe/ProjectScanner.exe) from the [Releases](https://github.com/sygkanrt30/project-scanner/releases/tag/exe) page.

 **No Java required!**

### Option 2: Build from Source (Cross-platform)

**Requirements:**
- Java 21 or higher
- Maven 3.6+

**Clone and build:**
```bash
git clone https://github.com/sygkanrt30/ProjectScanner.git
cd ProjectScanner
mvn clean package
```

**Run:**
```bash
java ProjectScanner.java [project_path] [output_file]
```

---

##  Usage

```bash
# Scan current directory, output to project_code.txt
ProjectScanner.exe

# Scan specified directory
ProjectScanner.exe C:\MyProject

# Scan with custom output file
ProjectScanner.exe C:\MyProject project_snapshot.txt
```

### Command Line Parameters

| Parameter | Description | Default |
|-----------|-------------|---------|
| `[project_path]` | Path to the project root directory | `.` (current directory) |
| `[output_file]` | Name of the output file | `project_code.txt` |

---

##  Example Output

```
PROJECT STRUCTURE:
==================
pom.xml
src/main/java/ru/yanin/project_scanner/ProjectScanner.java

SOURCE CODE:
============

================================================================================
FILE: pom.xml
================================================================================
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0">
    ...
</project>

================================================================================
FILE: src/main/java/ru/yanin/project_scanner/ProjectScanner.java
================================================================================
package ru.yanin.project_scanner;

import java.io.FileWriter;
...
```

---

##  Included File Extensions

The scanner processes files with these extensions:

| Type | Extensions |
|------|------------|
| **Languages** | `.java`, `.gradle`, `.kt`, `.kts` |
| **Configurations** | `.xml`, `.yml`, `.yaml`, `.properties`, `.json` |
| **Documentation** | `.md`, `.txt` |

---

##  Excluded Directories

These directories are automatically skipped:

```
.git  |  build  |  target  |  out  |  bin  |  .idea  |  node_modules
```

---

##  Building EXE from Source (Optional)

If you want to generate the EXE file yourself:

```bash
# Build the JAR with dependencies
mvn clean package

# Use jpackage to create EXE (requires jpackage which is included in JDK 14+)
jpackage --input target \
         --name ProjectScanner \
         --main-jar ProjectScanner-1.0-SNAPSHOT-jar-with-dependencies.jar \
         --main-class ru.yanin.project_scanner.ProjectScanner \
         --type app-image \
         --win-console
```

> **Note:** The generated EXE will be in the `ProjectScanner` folder. You can copy it anywhere and run it.

---

##  Author

**Yanin Vyacheslav**

- GitHub: [@sygkanrt30](https://github.com/sygkanrt30)
- Email: slava.vy.2006@gmail.com

---

## ⭐ Support

If you find this project useful, please consider giving it a star ⭐ on GitHub!
