**POM**

- Maintainable testing code
- Reducing duplicates in code
- Adding a layer of abstraction
- Separating test from technical implementation

**Notes**:

- The page object will contain the representation of the page, and the services the page provides via methods
- No code related to what is being tested should be within the page object.
- Page objects themselves should never make verifications or assertions.
- There is one, single, verification which can, and should, be within the page object and that is to verify that the
  page, and possibly critical elements on the page, were loaded correctly. This verification should be done while
  instantiating the page object.


- **Summary**
- The public methods represent the services that the page offers
- Try not to expose the internals of the page
- Generally don’t make assertions
- Methods return other PageObjects
- Need not represent an entire page
- Different results for the same action are modelled as different methods

[Reference source](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/)

Advance concept | Component page

- simple locator / avoid duplicate locator when narrow down searching
- reuse component - code UI reuse => Test reuse component
- Components extent component & BasePage extent component & Other page extend BasePage
