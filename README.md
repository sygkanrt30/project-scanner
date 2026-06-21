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
-  **Supports 16+ programming languages** with predefined configurations

---

##  Usage

```bash
# Scan current directory with Java configuration (default)
ProjectScanner.exe java

# Scan with Python configuration
ProjectScanner.exe python

# Scan specific directory with language
ProjectScanner.exe java C:\MyProject

# Scan with custom output file and language
ProjectScanner.exe python C:\MyProject project_snapshot.txt

# Language names are case-insensitive
ProjectScanner.exe JAVA
ProjectScanner.exe JavaScript
ProjectScanner.exe csharp
```

### Command Line Arguments

```bash
ProjectScanner.exe [language] [project_path] [output_file]
```

| Argument | Description | Default | Required |
|----------|-------------|---------|----------|
| `language` | Programming language (see table below) | `java` | ✅ Yes |
| `project_path` | Path to the project root directory | `.` (current directory) | ❌ No |
| `output_file` | Name of the output file | `project_code.txt` | ❌ No |

**Example:**
```bash
# Scan Python project in current directory
ProjectScanner.exe python

# Scan Go project in specific folder with custom output
ProjectScanner.exe golang D:\MyGoProject go_snapshot.txt
```

---

## 🔧 Supported Languages

The scanner comes with **16 predefined language configurations**. Each configuration includes:
- **Included extensions** - which file types to scan
- **Excluded directories** - which folders to skip

| Language | Command Argument | Included Extensions |
|----------|------------------|---------------------|
| **Java** | `java` | `.java`, `.gradle`, `.kt`, `.kts`, `.xml`, `.yml`, `.yaml`, `.properties`, `.json`, `.md`, `.txt` |
| **Kotlin** | `kotlin` | `.kt`, `.kts`, `.java`, `.gradle`, `.xml`, `.yml`, `.yaml`, `.properties`, `.json`, `.md`, `.txt` |
| **Scala** | `scala` | `.scala`, `.sbt`, `.sc`, `.java`, `.gradle`, `.xml`, `.yml`, `.yaml`, `.properties`, `.json`, `.md`, `.txt` |
| **C#** | `csharp` | `.cs`, `.csproj`, `.sln`, `.config`, `.xml`, `.yml`, `.yaml`, `.json`, `.md`, `.txt`, `.resx` |
| **Python** | `python` | `.py`, `.pyi`, `.pyx`, `.ipynb`, `.yml`, `.yaml`, `.json`, `.ini`, `.cfg`, `.md`, `.txt`, `.toml`, `.sh` |
| **Ruby** | `ruby` | `.rb`, `.erb`, `.rake`, `.gemfile`, `.yml`, `.yaml`, `.json`, `.md`, `.txt`, `.ru`, `.thor` |
| **Go** | `golang` | `.go`, `.mod`, `.sum`, `.yml`, `.yaml`, `.json`, `.md`, `.txt`, `.sh`, `.proto` |
| **JavaScript** | `javascript` | `.js`, `.jsx`, `.ts`, `.tsx`, `.json`, `.yml`, `.yaml`, `.md`, `.txt`, `.html`, `.css`, `.scss`, `.less` |
| **TypeScript** | `typescript` | `.ts`, `.tsx`, `.js`, `.jsx`, `.json`, `.yml`, `.yaml`, `.md`, `.txt`, `.html`, `.css`, `.scss`, `.less` |
| **PHP** | `php` | `.php`, `.php7`, `.phar`, `.module`, `.inc`, `.yml`, `.yaml`, `.json`, `.md`, `.txt`, `.xml`, `.htaccess` |
| **Rust** | `rust` | `.rs`, `.toml`, `.lock`, `.yml`, `.yaml`, `.json`, `.md`, `.txt` |
| **Swift** | `swift` | `.swift`, `.xcodeproj`, `.xcworkspace`, `.plist`, `.yml`, `.yaml`, `.json`, `.md`, `.txt`, `.strings` |
| **Perl** | `perl` | `.pl`, `.pm`, `.t`, `.pod`, `.yml`, `.yaml`, `.json`, `.md`, `.txt`, `.cgi` |
| **Shell** | `shell` | `.sh`, `.bash`, `.zsh`, `.fish`, `.ksh`, `.profile`, `.rc`, `.yml`, `.yaml`, `.json`, `.md`, `.txt` |
| **Dart** | `dart` | `.dart`, `.yaml`, `.json`, `.md`, `.txt`, `.html`, `.css`, `.js` |
| **R** | `r` | `.r`, `.rmd`, `.rdata`, `.rds`, `.yml`, `.yaml`, `.json`, `.md`, `.txt`, `.rnw` |

> **💡 Note**: Language names are **case-insensitive**. Use `java`, `JAVA`, `Java` - all work the same!

### Excluded Directories (Common for All Languages)

The scanner automatically excludes these directories for all languages:
```
.git  |  build  |  target  |  out  |  bin  |  .idea  |  node_modules  |  .vscode
```

Each language configuration may also have **language-specific exclusions**:
- **Java/Kotlin/Scala**: `.gradle`
- **Python**: `__pycache__`, `venv`, `.env`, `*.egg-info`, `.pytest_cache`
- **C#**: `obj`, `packages`, `.vs`
- **Ruby**: `vendor`, `.bundle`, `tmp`, `log`
- **Rust**: `debug`, `release`
- **Go**: `vendor`
- **Swift**: `DerivedData`, `Pods`
- **PHP**: `var`, `cache`, `logs`
- **Scala**: `.metals`, `project`

---

## 📄 Example Output

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

## 📦 Installation Options

### Option 1: Download EXE (Windows - Recommended)

Download the latest version of [**ProjectScanner.exe**](https://github.com/sygkanrt30/project-scanner/releases/download/exe/ProjectScanner.exe) from the [Releases](https://github.com/sygkanrt30/project-scanner/releases/tag/exe) page.

✅ **No Java required!**

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
java ProjectScanner.java [language_name] [project_path] [output_file]
```

---

##  Building EXE from Source (Optional)

If you want to generate the EXE file yourself:

```bash
mvn clean package
```

The EXE will appear in the `target/` folder as `ProjectScanner.exe` - Self-contained and ready to use!

> **Note:** You can copy the EXE anywhere and run it.

---

##  Author

**Yanin Vyacheslav**

- GitHub: [@sygkanrt30](https://github.com/sygkanrt30)
- Email: slava.vy.2006@gmail.com

---

## ⭐ Support

If you find this project useful, please consider giving it a star ⭐ on GitHub!
