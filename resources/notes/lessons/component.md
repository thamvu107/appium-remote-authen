- Component Based Model
    - Narrow down searching scope

    - Annotation
        - Retention:
            - enum RetentionPolicy
        - Target
            - ElementType[] value();

    - Generic type

- Annotation:
    - Categories of Annotations: (5)
        1. Marker Annotations:
            - Ex: @TestAnnotation()
        2. Single value Annotations
            - Ex: @TestAnnotation(“testing”);
        3. Full Annotations
            - Ex: @TestAnnotation(owner=”Rahul”, value=”Class Geeks”)
        4. Type Annotations
        5. Repeating Annotations

    - User-defined (Custom) :
        - `[Access Specifier] @interface<AnnotationName>
          {         
          DataType <Method Name>() [default value];
          }`
        - The return type of method should be either primitive, enum, string, class name, or array of primitive, enum,
          string, or class name type.


- Generic in java:
    - Types of Java Generics:
        - Generic Method
        - Generic Classes
        - Generic Class
        - Note:
            - Generics Work Only with Reference Types ( String, Integer,..)
            - We cannot use primitive data types like: int, char.
            - Generic Types Differ Based on Their Type Arguments:
        - Type Parameters in Java Generics: (naming conventions)
            - T – Type
            - E – Element
            - K – Key
            - N – Number
            - V – Value
    - Advantages of Generics:
        - Code Reuse
        - Type safety:
            - Generics make errors to appear compile time than at run time (It’s always better to know problems in your
              code at compile time rather than making your code fail at run time). Suppose you want to create an
              ArrayList that store name of students, and if by mistake the programmer adds an integer object instead of
              a string, the compiler allows it. But, when we retrieve this data from ArrayList, it causes problems at
              runtime.
        - Individual Type Casting is not needed
        - Generics Promotes Code Reusability
        - Implementing Generic Algorithms:
            - By using generics, we can implement algorithms that work on different types of objects, and at the same,
              they are type-safe too.
