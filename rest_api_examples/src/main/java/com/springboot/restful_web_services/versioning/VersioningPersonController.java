package com.springboot.restful_web_services.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    //URI Versioning
    @GetMapping("/v1/person")
    public PersonV1 getV1Person() {
        return new PersonV1("Ajay Murtekar");
    }

    @GetMapping("v2/person")
    public PersonV2 getV2Person() {
        return new PersonV2(new Name("Ajay", "Murtekar"));
    }


    //Param versioning
    @GetMapping(path = "/person", params = "version=v1")
    public PersonV1 getV1PersonWithParam() {
        return new PersonV1("Ajay Murtekar");
    }

    @GetMapping(path = "/person", params = "version=v2")
    public PersonV2 getV2PersonWithParam() {
        return new PersonV2(new Name("Ajay", "Murtekar"));
    }


    //Header versioning
    @GetMapping(path = "/person/header", headers = "x-api-version=1")
    public PersonV1 getV1PersonWithHeaders() {
        return new PersonV1("Ajay Murtekar");
    }

    @GetMapping(path = "/person/header", headers = "x-api-version=2")
    public PersonV2 getV2PersonWithHeaders() {
        return new PersonV2(new Name("Ajay", "Murtekar"));
    }

    //Media type/ content-negotiation versioning
    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getV1PersonWithMediaType() {
        return new PersonV1("Ajay Murtekar");
    }

    @GetMapping(path="/person/accept", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getV2PersonWithMediaType() {
        return new PersonV2(new Name("Ajay", "Murtekar"));
    }
}
