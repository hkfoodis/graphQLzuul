package org.zerock.shoppingcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;

import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.zerock.shoppingcart.service.ProductService;


@RestController
public class graphqlcontroller {
	
	protected Logger logger = Logger.getLogger(graphqlcontroller.class
			.getName());
	
	private final GraphQL graphQL;
	
	@Autowired
    public graphqlcontroller(ProductService productSerivce) {

        //Schema generated from query classes
        GraphQLSchema schema = new GraphQLSchemaGenerator()
                .withBasePackages("org.zerock.shoppingcart")
                .withOperationsFromSingletons(productSerivce)
                .generate();
        graphQL = GraphQL.newGraphQL(schema).build();

    }
	
	/*@PostMapping(value = "/graphql", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> indexFromAnnotated(@RequestBody Map<String, String> request, HttpServletRequest raw) {
		ExecutionResult executionResult = graphQL.execute(ExecutionInput.newExecutionInput()
				.query(request.get("query"))
	            .operationName(request.get("operationName"))
	            .context(raw)
	            .build());
	    return executionResult.toSpecification();
	}*/

}
