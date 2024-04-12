# classscheduler2

## Run Config
Main class:  `classscheduler.SchedulerMain`  
VM Arguments:  none  
Program Arguments:  See below

## Run Option Full Run
This generates student schedules based on student activity selections and activity availability.
Program Arguments:  none  
Output: `activity_report.html`, `student_report.html` and `all_student_schedule.csv`.

## Run Option Generate Reports Only
This step simply regenerates the reports (html files) from the current schedule according to the `all_student_schedule.csv` file.

To do this; pass Program Program Arguments:  `runreports`.
  
## Input Files
`ActivitySetup.csv` - this contains configuration for the Activity  
`StudentActivities.csv` - this contains the student activities selections in order of priority  
`all_student_schedule.csv` - this is an optional input used ONLY for the `runreports` option.  This file is created and output by the `fullrun` option  

##Output Files
`activity_report.html` - html document activity roster (contains activities with students in those activities)  This are the roster sheet for the instructor of each class.  
 `student_report.html` - html document of activities for each student.  This is an activity agenda sheet for each student.  
  `all_student_schedule.csv` - This file is created and output by the `fullrun` option.  This file is essentially a csv of the student schedules (i.e student x activities).  This file is then manually updated to schedule/fill unscheduled blocks for students.  It is re-uploaded and input to 'runreports'  

2023 - Add reports to following cloud drive:
OneDrive folder: https://onedrive.live.com/?authkey=%21AJFvj94HQTurMlM&id=89D6F74035CB31F8%212546&cid=89D6F74035CB31F8
OR
If the OneDrive folder does not work please use this Goolge Drive location: https://drive.google.com/drive/folders/1N3YiFI0QzszAiavd4EWvUTEYJ80Dc-bW?usp=sharing
