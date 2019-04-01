package classscheduler;

import org.apache.log4j.Logger;

import java.util.List;

import static classscheduler.SchedulerConstants.NUMBER_OF_STUDENT_ACTIVITY_SELECTIONS;

public class ScheduleScorer {
    private static final Logger LOGGER = Logger.getLogger(ScheduleScorer.class);

    Integer scoreBool = 0;
    Integer scoreRank = 0;

    public void score(List<Student> studentChoices) {
        for (Student student : studentChoices) {
            List<Boolean> scheduledActivities = student.getActSchedList();
            int numActivities = scheduledActivities.size();
            for (int i = 0; i < numActivities; i++) {
                if (scheduledActivities.get(i).equals(true)) {
                    scoreBool();
                    scoreRank(i);
                }
            }
        }
        LOGGER.info("************************ Simple Scheduling Score=" + scoreBool);
        LOGGER.info("************************ Weighted Scheduling Score=" + scoreRank);
    }

    private void scoreRank(int priority) {
        scoreRank += (NUMBER_OF_STUDENT_ACTIVITY_SELECTIONS - priority);
    }

    private void scoreBool() {
        scoreBool++;
    }
}
