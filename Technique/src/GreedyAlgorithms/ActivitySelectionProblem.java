package GreedyAlgorithms;

import java.util.ArrayList;

/**
 * Created by Ethan on 16/1/1.
 * Activity-selection problem: scheduling several competing activities that require exclusive use of a common resource,
 * with a goal of selecting a maximum-size set of mutually compatible activities.
 */
public class ActivitySelectionProblem {
    public static class Activity {
        private static int nextIndex = 1;

        private int index = 1;
        private int start;  // start time
        private int finish; // finish time

        Activity(int start, int finish) {
            this.start = start;
            this.finish = finish;
            this.index = nextIndex;
            nextIndex++;
        }

        public int getStart() {
            return start;
        }

        public int getFinish() {
            return finish;
        }

        public int getIndex() {
            return index;
        }
    }

    public static Activity[] greedyActivitySelector(Activity[] activities) {
        int n = activities.length;
        ArrayList<Activity> compatibleActivities = new ArrayList<>();
        compatibleActivities.add(activities[0]);
        int k = 0; // indexes the most recent addition to A
        for (int i = 1; i < n; i++) {
            if (activities[i].getStart() >= activities[k].getFinish()) {
                compatibleActivities.add(activities[i]);
                k = i;
            }
        }
        return compatibleActivities.toArray(new Activity[compatibleActivities.size()]);
    }

    public static void printResult(Activity[] compatibleActivities) {
        for (Activity compatibleActivity : compatibleActivities) {
            System.out.print("A" + compatibleActivity.getIndex() + " ");
        }
    }

    public static void main(String[] args) {
        Activity[] activities = {new Activity(1, 4), new Activity(3, 5), new Activity(0, 6), new Activity(5, 7),
                new Activity(3, 9), new Activity(5, 9), new Activity(6, 10), new Activity(8, 11), new Activity(8, 12),
                new Activity(2, 14), new Activity(12, 16)};
        printResult(greedyActivitySelector(activities));
    }
}
