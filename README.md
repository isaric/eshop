Welcome to the e-shop application. This is a demo project was build using gradle.

In order to run the application simply go to the project root and run

    gradle bootRun
    
In order to package the application into a jar file simply go to the project root
and run

    gradle bootJar
    
Provided Endpoints:

    root url: http://localhost:8080
   
 1.Create new product

    POST /product
    request body: { "name" : "car", "price": 20000.00}
    
    Throws a 400 response if the name or price are not specified.
    Returns the newly created product with its id.
    
 2.Update a product
 
     PUT /product
     request body: { "id": 1, "name" : "better car", "price" : 40000.00}
     
     Throws a 404 response if the product does not exist.
     Throws a 400 response if the name or price are not specified.
     Returns the upated product with its id.
     
 3.Get all products
 
     GET /product
     
 4.Create an order
 
     POST /order
     request body: {"email": "test@test.com", "productIds" : [1]}
     
     Throws a 404 if any of the products don't exist or a 400 if the email
     is not specified.
     Returns the order with specified total price and date of creation.
     
 5.Search order by date created
 
     GET /order
     request body: {"begin" : "dd-MM-yyyy hh:mm:ss", "end" : "dd-MM-yyyy hh:mm_ss" }
     
     Throws a 400 if both fields are not specified in the valid format.
     Returns all orders with the interval of the begin and end date.