package gitdone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class StudentScore {
    private String name;
    private String id;
    private String course;
    private int score;

    public StudentScore(String name, String id, String course, int score) {
        this.name = name;
        this.id = id;
        this.course = course;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getCourse() {
        return course;
    }

    public int getScore() {
        return score;
    }
}

public class ScoreManagementSystem {
    private List<StudentScore> scores = new ArrayList<>();
    private Map<String, Boolean> idMap = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    // 记录学生成绩
    public void recordScore() {
        System.out.println("请输入学生姓名:");
        String name = scanner.nextLine();
        System.out.println("请输入学生学号:");
        String id = scanner.nextLine();
        if (idMap.containsKey(id)) {
            System.out.println("该学号已存在，请重新输入！");
            return;
        }
        System.out.println("请输入课程名称:");
        String course = scanner.nextLine();
        System.out.println("请输入成绩(0-100分):");
        int score = scanner.nextInt();
        scanner.nextLine();
        if (score < 0 || score > 100) {
            System.out.println("成绩超出范围，请重新输入！");
            return;
        }
        StudentScore studentScore = new StudentScore(name, id, course, score);
        scores.add(studentScore);
        idMap.put(id, true);
        System.out.println("成绩记录成功！");
    }

    // 查询学生成绩
    public void queryScore() {
        System.out.println("请选择查询方式：1.按学生姓名 2.按学生学号 3.按课程名称");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                System.out.println("请输入学生姓名:");
                String name = scanner.nextLine();
                queryByName(name);
                break;
            case 2:
                System.out.println("请输入学生学号:");
                String id = scanner.nextLine();
                queryById(id);
                break;
            case 3:
                System.out.println("请输入课程名称:");
                String course = scanner.nextLine();
                queryByCourse(course);
                break;
            default:
                System.out.println("无效的查询方式，请重新选择！");
        }
    }

    private void queryByName(String name) {
        boolean found = false;
        for (StudentScore score : scores) {
            if (score.getName().equals(name)) {
                printScore(score);
                found = true;
            }
        }
        if (!found) {
            System.out.println("没有找到该学生的成绩记录！");
        }
    }

    private void queryById(String id) {
        boolean found = false;
        for (StudentScore score : scores) {
            if (score.getId().equals(id)) {
                printScore(score);
                found = true;
            }
        }
        if (!found) {
            System.out.println("没有找到该学生的成绩记录！");
        }
    }

    private void queryByCourse(String course) {
        boolean found = false;
        for (StudentScore score : scores) {
            if (score.getCourse().equals(course)) {
                printScore(score);
                found = true;
            }
        }
        if (!found) {
            System.out.println("没有找到该课程的成绩记录！");
        }
    }

    // 统计成绩
    public void statisticScore() {
        System.out.println("请输入课程名称:");
        String course = scanner.nextLine();
        List<Integer> scoresForCourse = new ArrayList<>();
        for (StudentScore score : scores) {
            if (score.getCourse().equals(course)) {
                scoresForCourse.add(score.getScore());
            }
        }
        if (scoresForCourse.isEmpty()) {
            System.out.println("没有找到该课程的成绩记录！");
            return;
        }
        int sum = 0;
        int max = scoresForCourse.get(0);
        int min = scoresForCourse.get(0);
        for (int s : scoresForCourse) {
            sum += s;
            if (s > max) {
                max = s;
            }
            if (s < min) {
                min = s;
            }
        }
        double average = (double) sum / scoresForCourse.size();
        System.out.println(course + "的平均分是: " + average);
        System.out.println(course + "的最高分是: " + max);
        System.out.println(course + "的最低分是: " + min);
    }

    // 打印成绩
    private void printScore(StudentScore score) {
        System.out.println("学生姓名: " + score.getName());
        System.out.println("学生学号: " + score.getId());
        System.out.println("课程名称: " + score.getCourse());
        System.out.println("成绩: " + score.getScore());
    }

    // 显示菜单
    public void showMenu() {
        System.out.println("请选择操作：");
        System.out.println("1. 记录学生成绩");
        System.out.println("2. 查询学生成绩");
        System.out.println("3. 统计成绩");
        System.out.println("4. 退出");
    }

    public static void main(String[] args) {
        ScoreManagementSystem system = new ScoreManagementSystem();
        while (true) {
            system.showMenu();
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    system.recordScore();
                    break;
                case 2:
                    system.queryScore();
                    break;
                case 3:
                    system.statisticScore();
                    break;
                case 4:
                    System.out.println("感谢使用，程序退出！");
                    System.exit(0);
                default:
                    System.out.println("无效的选择，请重新输入！");
            }
        }
    }
}