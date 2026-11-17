package com.klef.soa.docker.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.klef.soa.docker.entity.SoftwareTrainingBatch;
import com.klef.soa.docker.repository.SoftwareTrainingBatchRepository;

@SpringBootTest
public class TrainerBatchIntegrationTest
{
    @Autowired
    private SoftwareTrainingBatchRepository repository;


    // =========================================================
    // TEST CASE 1
    // APPLICATION AND DATABASE CONNECTION
    // =========================================================

    @Test
    void testApplicationAndDatabaseConnection()
    {
        System.out.println();
        System.out.println("==========================================");
        System.out.println("TEST CASE 1 : APPLICATION DATABASE CHECK");
        System.out.println("==========================================");

        assertNotNull(repository);

        long count = repository.count();

        assertTrue(count >= 0);

        System.out.println("Repository connected successfully");
        System.out.println("Total Batches: " + count);

        System.out.println("TC-1 PASSED");
    }


    // =========================================================
    // TEST CASE 2
    // FETCH ALL TRAINING BATCHES
    // =========================================================

    @Test
    void testFetchAllBatches()
    {
        System.out.println();
        System.out.println("==========================================");
        System.out.println("TEST CASE 2 : FETCH ALL BATCHES");
        System.out.println("==========================================");

        List<SoftwareTrainingBatch> batches =
                repository.findAll();

        assertNotNull(batches);

        System.out.println(
                "Total Batches Found: " + batches.size()
        );

        for (SoftwareTrainingBatch batch : batches)
        {
            System.out.println("--------------------------------");
            System.out.println("Batch ID: " + batch.getId());
            System.out.println("Course: " + batch.getCourseTitle());
            System.out.println("Trainer: " + batch.getTrainerName());
            System.out.println("Learners: " + batch.getLearnerCount());
            System.out.println("Online: " + batch.getOnline());
        }

        System.out.println("--------------------------------");
        System.out.println("TC-2 PASSED");
    }


    // =========================================================
    // TEST CASE 3
    // CREATE AND SAVE TRAINING BATCH
    // =========================================================

    @Test
    void testCreateTrainingBatch()
    {
        System.out.println();
        System.out.println("==========================================");
        System.out.println("TEST CASE 3 : CREATE TRAINING BATCH");
        System.out.println("==========================================");

        SoftwareTrainingBatch batch =
                new SoftwareTrainingBatch();

        batch.setCourseTitle("Java Full Stack");
        batch.setTrainerName("Test Trainer");
        batch.setLearnerCount(30);
        batch.setOnline(true);

        SoftwareTrainingBatch savedBatch =
                repository.save(batch);

        assertNotNull(savedBatch);
        assertNotNull(savedBatch.getId());

        System.out.println(
                "New Batch Created Successfully"
        );

        System.out.println(
                "Batch ID: " + savedBatch.getId()
        );

        System.out.println(
                "Course: " + savedBatch.getCourseTitle()
        );

        System.out.println("TC-3 PASSED");
    }


    // =========================================================
    // TEST CASE 4
    // VERIFY TRAINING BATCH DATA
    // =========================================================

    @Test
    void testBatchData()
    {
        System.out.println();
        System.out.println("==========================================");
        System.out.println("TEST CASE 4 : VERIFY BATCH DATA");
        System.out.println("==========================================");

        List<SoftwareTrainingBatch> batches =
                repository.findAll();

        assertNotNull(batches);

        if (!batches.isEmpty())
        {
            SoftwareTrainingBatch batch =
                    batches.get(0);

            assertNotNull(batch.getCourseTitle());
            assertNotNull(batch.getTrainerName());
            assertNotNull(batch.getLearnerCount());
            assertNotNull(batch.getOnline());

            System.out.println(
                    "Batch data verified successfully"
            );
        }

        System.out.println("TC-4 PASSED");
    }
}