CREATE
//test specialities
(engineer:SPECIALITY {name: "engineer", description: "engineer"}),
(marketer:SPECIALITY {name: "marketer", description: "marketer"}),

//test business plan
(new_product_specification:BUSINESS_PLAN_SPECIFICATION {risks: "no risks", opportunity: "new customers", necessary_funds: "9000.00", profit: "12000.00"}),

(new_product_statistics:BUSINESS_PLAN_STATISTICS {all_tasks: 2, completed_tasks: 0, deadline: date("2022-12-12")}),

(new_product_plan:BUSINESS_PLAN {title:"brand new product", status: "APPROVED"}),
(new_product_plan)-[:HAS_PLAN_SPECIFICATION]->(new_product_specification),
(new_product_plan)-[:HAS_PLAN_STATISTICS]->(new_product_statistics),


//test tasks
//1
(make_new_product_task:TASK {title: "make new product", description: "make new product", deadline: date("2022-12-9"), status: "IN_PROCESS", business_plan_id: ID(new_product_plan)}),
(make_new_product_task)-[:NECESSARY_SPECIALITY]->(engineer),

//2
(make_advertisement_task:TASK {title: "make a product ad", description: "make a product ad", deadline: date("2022-12-12"), status: "IN_PROCESS", business_plan_id: ID(new_product_plan)}),
(make_advertisement_task)-[:NECESSARY_SPECIALITY]->(marketer),

(new_product_plan)-[:HAS_TASKS]->(make_new_product_task),
(new_product_plan)-[:HAS_TASKS]->(make_advertisement_task),

// test employees
//1
(alex_stats:EMPLOYEE_STATISTICS {completed_tasks: 3, completed_on_time: 2, completed_after_deadline: 1, efficiency: 66}),

(alex_employee_profile:EMPLOYEE_PROFILE {role: "DIRECTOR"}),
(alex_employee_profile)-[:HAS_SPECIALITY]->(engineer),

(alex:USER {name: "Alex", surname: "Alex", age: 29, phone_number: "+375 (29) 111-11-11",login: "alex", password: "$2a$12$KRuK7UBl2/C4XaolNTQ8sO/W5CgwPdgh//S4YVYqYbUDOJuk8T5zO"}),
(alex)-[:HAS_PROFILE_STATISTICS]->(alex_stats),
(alex)-[:HAS_PROFILE]->(alex_employee_profile),

//2
(jhon_stats:EMPLOYEE_STATISTICS {completed_tasks: 1, completed_on_time: 1, completed_after_deadline: 0, efficiency: 100}),

(jhon_employee_profile:EMPLOYEE_PROFILE {role: "EMPLOYEE"}),
(jhon_employee_profile)-[:HAS_SPECIALITY]->(engineer),
(jhon_employee_profile)-[:HAS_TASKS]->(make_new_product_task),

(jhon:USER {name: "Jhon", surname: "Jhon", age: 26, phone_number: "+375 (29) 555-55-55",login: "jhon", password: "$2a$12$pd0QISHzdzVzlDKQD7xYp.CDRkSz1lf.eAKUP0rJuQKKqGDBybmdG"}),
(jhon)-[:HAS_PROFILE_STATISTICS]->(jhon_stats),
(jhon)-[:HAS_PROFILE]->(jhon_employee_profile),

//3
(max_stats:EMPLOYEE_STATISTICS {completed_tasks: 0, completed_on_time: 0, completed_after_deadline: 0, efficiency: 0}),

(max_employee_profile: EMPLOYEE_PROFILE {role: "ANALYST"}),
(max_employee_profile)-[:HAS_SPECIALITY]->(marketer),
(max_employee_profile)-[:HAS_TASKS]->(make_advertisement_task),

(max:USER {name: "Max", surname: "Max", age: 18, phone_number: "+375 (29) 777-77-77",login: "max", password: "$2a$12$KWPu/kIxsfYOxyB0oDSGX.YprjUlpPdTzSTwsQMGsU2fs0Qj13oP6"}),
(max)-[:HAS_PROFILE_STATISTICS]->(max_stats),
(max)-[:HAS_PROFILE]->(max_employee_profile),


// test applications to membership
//1
(ivan_stats:EMPLOYEE_STATISTICS {completed_tasks: 3, completed_on_time: 2, completed_after_deadline: 1, efficiency: 66}),

(ivan:USER {name: "Ivan", surname: "Ivan", age: 29, phone_number: "+375 (29) 111-11-11",login: "ivan", password: "$2a$12$KRuK7UBl2/C4XaolNTQ8sO/W5CgwPdgh//S4YVYqYbUDOJuk8T5zO"}),
(ivan)-[:HAS_PROFILE_STATISTICS]->(ivan_stats),

//test org
(food_shop:ORGANIZATION {name: "food shop", available_funds: "15000.00"}),

(food_shop)-[:HAS_SPECIALITIES]->(engineer),
(food_shop)-[:HAS_SPECIALITIES]->(marketer),

(food_shop)-[:HAS_EMPLOYEES]->(alex_employee_profile),
(food_shop)-[:HAS_EMPLOYEES]->(jhon_employee_profile),
(food_shop)-[:HAS_EMPLOYEES]->(max_employee_profile),

(ivan)-[:SENT_REQUEST_TO_JOIN_ORGANIZATION]->(food_shop),

(food_shop)-[:HAS_BUSINESS_PLANS]->(new_product_plan)
;