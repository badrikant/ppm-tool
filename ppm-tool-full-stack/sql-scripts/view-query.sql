select * from ppmtcourse.backlog;
select * from ppmtcourse.project;
select * from ppmtcourse.project_task;

# find number of project tasks created
select count(*) backlog_id from ppmtcourse.project_task;

# find number of projects available 
select count(*) id from ppmtcourse.project;