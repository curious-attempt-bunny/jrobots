import java.util.List;
import java.util.ArrayList;

class __Bunny2PreciseShoot_ extends JJRobot {
    void main() {
        int observedCount = 0;
        double[][] observed = new double[][] { new double[3], new double[3], new double[3] };

        List<JJVector> observations = new ArrayList<JJVector>();

        while(true) {
            for(int angle=0; angle<360; angle+=1) {
                int dist = scan(angle, 1);
                if (dist > 0) {

                    JJVector observation = JJVector.Polar(dist, angle, time());

                    observations.add(observation);
                    while(observations.size() > 2 && observations.get(observations.size()-1).t() - observations.get(0).t() > 1.0) {
                        observations.remove(0);
                    }

                    if (observations.size() > 1) {
                        JJVector vector = observations.get(observations.size()-1).minus(observations.get(0));
                        vector = vector.mult(1.0 / vector.t()).mult(1.0 / 100.0);

                        JJVector position = observation;
                        double missileDist = 0;

                        while(missileDist < 1500) {
                            missileDist += 300 / 100.0;
                            position = position.plus(vector);
                            if (position.mag() <= missileDist) {
                                cannon((int)position.angle(), (int)position.mag());
                                break;
                            }
                        }
                    } else {
                        cannon((int)observation.angle(), (int)observation.mag());
                    }
               }
            }
        }
    }
}