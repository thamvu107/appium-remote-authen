Waiting strategies:

1. Static Wait/Hard Wait: Wait for a hard-conde amount of time.
    - Thread.sleep(5000)
2. Implicit Wait:
    - Implicit waits provide a global wait time for elements to be available.
    - Only for finding elements. Server-side retry modulated by the implicit wait timeout.
    - maximum 10 seconds ()
3. Explicit Wait:
    - Explicit waits offer more precise synchronization by waiting for specific conditions to be met.
    - Can wait for any kind of state. Client-side retry typical suiting built-in or custom expected condition.
    - Custom wait:
    - Fluent Wait: FluentWait provides advanced customization options for wait strategies.

 