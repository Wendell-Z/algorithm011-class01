package week04;

import java.util.HashSet;
import java.util.Set;

/**
 * 提示：
 * 0 <= commands.length <= 10000
 * 0 <= obstacles.length <= 10000
 * -30000 <= obstacle[i][0] <= 30000
 * -30000 <= obstacle[i][1] <= 30000
 * 答案保证小于 2 ^ 31
 */
public class P874_Walking_Robot_Simulation {
    public int _robotSim(int[] commands, int[][] obstacles) {
        int[] dx = new int[]{0, 1, 0, -1};
        int[] dy = new int[]{1, 0, -1, 0};
        // start position
        int x = 0, y = 0, di = 0;

        // Encode obstacles (x, y) as (x+30000) * (2^16) + (y+30000)
        // base on hint
        Set<Long> obstacleSet = new HashSet();
        for (int[] obstacle : obstacles) {
            long ox = (long) obstacle[0] + 30000;
            long oy = (long) obstacle[1] + 30000;
            obstacleSet.add((ox << 16) + oy);
        }

        int ans = 0;
        for (int cmd : commands) {
            if (cmd == -2)  //left
                di = (di + 3) % 4;
            else if (cmd == -1)  //right
                di = (di + 1) % 4;
            else {
                for (int k = 0; k < cmd; ++k) {
                    int nx = x + dx[di];
                    int ny = y + dy[di];
                    long code = (((long) nx + 30000) << 16) + ((long) ny + 30000);
                    if (!obstacleSet.contains(code)) {
                        x = nx;
                        y = ny;
                        ans = Math.max(ans, x * x + y * y);
                    }
                }
            }
        }

        return ans;
    }

    public int robotSim(int[] commands, int[][] obstacles) {
        int x = 0;
        int y = 0;
        int face = 1; // 1 n -1 s 2 e -2 w
        int old = 0;
        for (int i = 0; i < commands.length; i++) {
            if (commands[i] == -1) {
                face = setFace(face, commands[i]);
                continue;
            }
            if (commands[i] == -2) {
                face = setFace(face, commands[i]);
                continue;
            }
            // judge direction
            // to n x y++
            if (face == 1) {
                // see if x in obstacles
                old = y;
                y = y + commands[i];
                for (int j = 0; j < obstacles.length; j++) {
                    if (obstacles[j][0] == x && obstacles[j][1] <= y && obstacles[j][1] > old) {
                        y = obstacles[j][1] - 1;
                        break;
                    }
                }
            }
            // to s x y--
            if (face == -1) {
                // see if x in obstacles
                old = y;
                y = y - commands[i];
                for (int j = 0; j < obstacles.length; j++) {
                    if (obstacles[j][0] == x && obstacles[j][1] >= y && obstacles[j][1] < old) {
                        y = obstacles[j][1] + 1;
                        break;
                    }
                }
            }
            // to e x++ y
            if (face == 2) {
                // see if y in obstacles
                old = x;
                x = x + commands[i];
                for (int j = 0; j < obstacles.length; j++) {
                    if (obstacles[j][1] == y && obstacles[j][0] <= x && obstacles[j][0] > old) {
                        x = obstacles[j][0] - 1;
                        break;
                    }
                }
            }
            // to w x-- y
            if (face == -2) {
                // see if y in obstacles
                old = x;
                x = x + commands[i];
                for (int j = 0; j < obstacles.length; j++) {
                    if (obstacles[j][1] == y && obstacles[j][0] >= x && obstacles[j][0] < old) {
                        x = obstacles[j][0] + 1;
                        break;
                    }
                }
            }
        }

        return (int) (Math.pow(x, 2) + Math.pow(y, 2));

    }

    private int setFace(int face, int command) {
        if (command == -1) {
            if (face == 1) return 2;
            if (face == 2) return -1;
            if (face == -1) return -2;
            if (face == -2) return 1;
        } else {
            if (face == 1) return -2;
            if (face == 2) return 1;
            if (face == -1) return 2;
            if (face == -2) return -1;
        }
        return face;
    }

    public static void main(String[] args) {
        P874_Walking_Robot_Simulation p = new P874_Walking_Robot_Simulation();
        int[] c = {2, 2, 5, -1, -1};
        int[][] o = {{-3, 5}, {-2, 5}, {3, 2}, {5, 0}, {-2, 0}, {-1, 5}, {5, -3}, {0, 0}, {-4, 4}, {-3, 4}};
        System.out.println(p.robotSim(c, o));
    }


}
