package keri.learrningspring.myfirstproject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

/* this class is going to define request mappings */
/* entry point into the application*/
/*It will return data*/

@RestController
public class GreetingController {
    private static final String template = "Hello, %s !";
    private final AtomicLong counter = new AtomicLong(); /* way to set the ID for greeting */
    private final GreetingRepository repository;
    private final EmployeeModelAssembler assembler;





    /* single entry point to application*/
    /* return a greeting object*/


    /*to make it accesible to outside world --> have to use request mapping*/
    @GetMapping("/greeting")          /* get request -> to greeting*/
    public Greeting greeting(@RequestParam(value = "name",defaultValue = "World" ) String name){
       /*request parameter  extracts id/name query parameter*/
        return new Greeting(counter.incrementAndGet(),String.format(template,name));
    }

    private final GreetingRepository repository;

    GreetingController(GreetingRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/greeting")
    ResponseEntity<?> newGreeting(@RequestBody Greeting newGreeting) {

        repository.save(newGreeting);

        EntityModel<Greeting> entityModel = assembler.toModel(repository.save(newEmployee));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }


}
