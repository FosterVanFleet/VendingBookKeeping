Vending Machine Business – Backend Bookkeeping System

Overview
This project is a backend bookkeeping system built to support my personal vending machine business.
The system is designed to:
  - Maintain structured financial records
  - Track expenses and profits
  - Store essential operational data
  - Generate monthly and yearly financial reports

The core objective was to design a scalable backend architecture that reads structured data from files, parses it into domain objects, and produces automated financial summaries.

This project is built using a Java 7 class-based architecture and follows scalable, object-oriented design principles.
Key Design Goals
  - Clear separation of responsibilities
  - Encapsulation of financial entities
  - Extensible report generation
  - Maintainable and modular codebase

OOP Principles Applied
  - Encapsulation – Business entities are modeled as structured domain objects
  - Abstraction – Parsing and report generation are abstracted from core data models
  - Single Responsibility Principle – Each class serves one clear purpose
  - Scalability – Architecture allows future extension (e.g., additional report types)

Data Processing Workflow:
  - Input File
    -  A structured .txt file contains:
        -  Expenses
        -  Revenue
        -  Profit-related entries
        -  Other essential business records
          
    - Object-Based Parsing
        - File data is parsed into strongly-typed Java objects
        - Each entry becomes a domain entity
        - Parsing logic is separated from business logic

    - Report Generation
        - Monthly report files are generated automatically
        - Yearly summaries are computed from parsed data
        - Output is written to new structured files

     This design ensures the system behaves like a lightweight financial data pipeline.

Features
  - Expense tracking
  - Profit calculation
  - Monthly financial reporting
  - Yearly financial reporting
  - Food Loss tracking
  - Structured file parsing
  - Object-oriented data modeling

Technologies
  - Java 7
  - File I/O
  - Object-oriented design
  - Text-based data storage

Future Improvements
  Potential enhancements include:
    - Database integration (MySQL / PostgreSQL)
    - REST API layer
    - UI dashboard
    - Automated inventory tracking
    - CSV or JSON export formats
    - Unit testing framework integration
    
Disclaimers:
  - All .txt files included in this repository are sample data only and do not reflect real business records.
  - The repository and ReadMe may not not reflect the most current or updated version of this program.
