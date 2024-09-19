- There are three ways to set these parameters: (https://testng.org/#_parameters)

- Parameters from testng.xml
    - When: simple value for parameters
    - Scope tag & order precedence (lowest to highest) : <suite> --> <test> --> <class> --> <methods>
- Parameters with DataProviders
    - when: Complex parameters, or parameters that need to be created from Java
        - EX: complex objects, objects read from a property file or a database, etc…
- Java system properties
