package mybootapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// Indicates that this class is a Spring MVC controller.
@Controller
// Maps all requests that start with "/secure" to methods in this controller.
@RequestMapping("/secure")
public class SecureController {

    // Automatically injects an instance of SecureService.
    @Autowired
    private SecureService ss;

    // A quick example of a method secured at the service layer. This endpoint returns a string response.
    // @ResponseBody indicates that the returned String is the response body and should not be used to select a view.
    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        // Delegates the call to a service method intended for admin users.
        return ss.helloAdmin();
    }

    // Demonstrates passing a parameter to a service method, possibly for role or user-specific processing.
    @ResponseBody
    @RequestMapping("/aaa")
    public String helloForUser() {
        // The string "aaa" could represent a user ID or role, depending on implementation.
        return ss.helloForUser("aaa");
    }

    // This might be an example of method-level security implemented within the service, based on Spring Security annotations.
    @ResponseBody
    @RequestMapping("/code")
    public String helloForUserByCode() {
        // This method's security is likely enforced through method security annotations in the SecureService.
        return ss.helloSecuredByCode();
    }

    // Consider adding more endpoints as needed, ensuring each is properly secured either through
    // Spring Security's URL-based security or method-level security annotations.

    // It's also a good practice to handle exceptions gracefully in your controller, possibly by using
    // @ExceptionHandler methods or a controller advice to handle common exceptions across controllers.
}
