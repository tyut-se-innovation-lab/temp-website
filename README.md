# temp-website
Temporary project used  by TYUT Software laboratory.
## Objective
We develop this system in order to exercise our member's code ability, so the performance of the system may be poor, but we enjoyed the development process.
## Feature
Now only the empty schedule system has been developed.
- user can upload their schedule by input or crawl from tyut official website by offer cookie.
- user can view the leisure people in the given time frame.
- administrator can arrange the schedule, but the schedule is not saved in this system.
## How to run
### Run java
1. start your redis server in 6379.
2. change the properties of database to yours properties.
3. run the sql file in your database, we recommend using Mysql.
4. use `mvn package`
5. use `sh ry.sh start`
### Run vue
1. use `cd ruoyi-ui`
2. use `npm install`
3. use `npm run dev`
