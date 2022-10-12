CREATE
(food_shop:ORGANIZATION {name: "food shop", available_funds: 15000}),

(engineer:SPECIALITY {name: "engineer", description: "engineer"}),
(engineer)-[:SPECIALITY_IN_ORGANIZATION]->(food_shop),
(marketer:SPECIALITY {name: "marketer", description: "marketer"}),
(marketer)-[:SPECIALITY_IN_ORGANIZATION]->(food_shop),

(alex:USER {name: "Alex", surname: "Alex", age: 29, phone_number: "+375 (29) 111-11-11",login: "login", password: "password"}),
(alex_stats:EMPLOYEE_STATISTICS {completed_tasks: 3, completed_on_time: 2, completed_after_deadline: 1, efficiency: 66}),
(alex)-[:HAS_PROFILE_STATISTICS]->(alex_stats),
(alex_employee_profile: EMPLOYEE_PROFILE {role: "admin"}),
(alex_employee_profile)-[:HAS_SPECIALITY]->(engineer),
(alex_employee_profile)-[:WORK_FOR_ORGANIZATION]->(food_shop),
(alex_employee_profile)-[:PROFILE_OF_USER]->(alex),

(jhon:USER {name: "Jhon", surname: "Jhon", age: 26, phone_number: "+375 (29) 555-55-55",login: "login", password: "password"}),
(jhon_stats:EMPLOYEE_STATISTICS {completed_tasks: 1, completed_on_time: 1, completed_after_deadline: 0, efficiency: 100}),
(jhon)-[:HAS_PROFILE_STATISTICS]->(jhon_stats),
(jhon_employee_profile: EMPLOYEE_PROFILE {role: "employee"}),
(jhon_employee_profile)-[:HAS_SPECIALITY]->(engineer),
(jhon_employee_profile)-[:WORK_FOR_ORGANIZATION]->(food_shop),
(jhon_employee_profile)-[:PROFILE_OF_USER]->(jhon),

(max:USER {name: "Max", surname: "Max", age: 18, phone_number: "+375 (29) 777-77-77",login: "login", password: "password"}),
(max_stats:EMPLOYEE_STATISTICS {completed_tasks: 0, completed_on_time: 0, completed_after_deadline: 0, efficiency: 0}),
(max)-[:HAS_PROFILE_STATISTICS]->(max_stats),
(max_employee_profile: EMPLOYEE_PROFILE {role: "analyst"}),
(max_employee_profile)-[:HAS_SPECIALITY]->(marketer),
(max_employee_profile)-[:WORK_FOR_ORGANIZATION]->(food_shop),
(max_employee_profile)-[:PROFILE_OF_USER]->(max),

(new_product_plan:BUSINESS_PLAN {title:"brand new product"}),
(new_product_plan)-[:BUSINESS_PLAN_BELONGING_TO_ORGANIZATION {status: "approved"}]->(food_shop),
(new_product_specification:BUSINESS_PLAN_SPECIFICATION {risks: "no risks", opportunity: "new customers", necessary_funds: 9000, profit: 12000}),
(new_product_plan)-[:HAS_PLAN_SPECIFICATION]->(new_product_specification),
(new_product_statistics:BUSINESS_PLAN_STATISTICS {all_tasks: 1, completed_tasks: 0, deadline: "10-12-2022"}),
(new_product_plan)-[:HAS_PLAN_STATISTICS]->(new_product_statistics),

(make_new_product_task:TASK {title: "make new product", description: "make new product", deadline: "9-12-2022", status: "in process"}),
(make_new_product_task)-[:TASK_OF_BUSINESS_PLAN]->(new_product_plan),
(make_new_product_task)-[:NECESSARY_SPECIALITY]->(engineer),
(make_new_product_task)-[:TASK_FOR_EMPLOYEE]->(jhon_employee_profile),

(make_advertisement_task:TASK {title: "make a product advertisement", description: "make a product advertisement", deadline: "10-12-2022", status: "in process"}),
(make_advertisement_task)-[:TASK_OF_BUSINESS_PLAN]->(new_product_plan),
(make_advertisement_task)-[:NECESSARY_SPECIALITY]->(marketer),
(make_advertisement_task)-[:TASK_FOR_EMPLOYEE]->(max_employee_profile)
;