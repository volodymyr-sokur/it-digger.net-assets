# Engineering Blog Assets: Benchmarks, Architecture & Lab Notes

This repository serves as the companion codebase for my technical blog at **[it-digger.net]**. 

While many technical blogs offer theory, this repository provides the **reproducible evidence**. Every article I write is backed by the experiments, raw data, and source code found in these directories.

## 🔬 The Laboratory: Hardware & Environment
Experiments are conducted across a variety of environments, with a special focus on **efficiency on constrained hardware**.

* **Primary Testbed:** HP EliteBook 2530p (Intel Core 2 Duo L9400, 8GB DDR2 RAM)
* **OS:** Xubuntu 24.04 LTS
* **Runtime Environments:** OpenJDK 21+, GraalVM, and various native toolchains.

## 📂 Project Structure
Each directory corresponds to a specific article or architectural deep-dive:

* **`/01-graalvm-vs-openjdk-basics`**: Comparative analysis of JIT vs. AOT on legacy silicon. 

## 🛠️ Philosophy: Performance as a Feature
The code in this repository focuses on:
1. **Resource Efficiency:** Getting the most out of every CPU cycle and MiB of RAM.
2. **Reproducibility:** Every folder contains a `commands.sh` or `Makefile` so you can verify the numbers yourself.
3. **Pragmatism:** Choosing the right tool (JIT vs. AOT, Microservice vs. Monolith) based on data, not hype.

---
**Author:** Volodymyr Sokur
**Blog:** it-digger.net
**License:** MIT
