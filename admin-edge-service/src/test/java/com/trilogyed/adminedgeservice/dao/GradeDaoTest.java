//package com.trilogyed.adminedgeservice.dao;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.*;
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
//public class GradeDaoTest {
//    @Autowired
//    private GradeDao gradeDao;
//    @Test
//    public void shouldGetGradesByStudent(){
//        int studentId=1;
//        Grade grade = new Grade();
//        grade.setAssignmentId(1);
//        grade.setStudentId(1);
//        grade.setPercentGrade(100);
//        grade.setId(1);
//        Grade grade2 = new Grade();
//        grade2.setAssignmentId(1);
//        grade2.setStudentId(1);
//        grade2.setPercentGrade(100);
//        grade2.setId(2);
//        List<Grade> gradesListExpected = new ArrayList<>();
//        gradesListExpected.add(grade);
//        gradesListExpected.add(grade2);
//        assertEquals(gradesListExpected,gradeDao.findGradeBySudentId(studentId));
//    }
//}