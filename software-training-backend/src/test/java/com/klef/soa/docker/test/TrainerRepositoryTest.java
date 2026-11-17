package com.klef.soa.docker.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.klef.soa.docker.entity.SoftwareTrainingBatch;
import com.klef.soa.docker.repository.SoftwareTrainingBatchRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class TrainerRepositoryTest 
{

    @Autowired
    private SoftwareTrainingBatchRepository repository;

    @Test
    void testFindAllBatches() 
    {
        List<SoftwareTrainingBatch> batches = repository.findAll();
        assertNotNull(batches);
        System.out.println("Total Batches: " + batches.size());
    }

    @Test
    void testFindBatchById() 
    {
        List<SoftwareTrainingBatch> batches = repository.findAll();
        assertFalse(batches.isEmpty());
        Long id = batches.get(0).getId();
        Optional<SoftwareTrainingBatch> batch = repository.findById(id);
        assertTrue(batch.isPresent());
        assertEquals(id, batch.get().getId());
        System.out.println("Batch ID: " + batch.get().getId());
        System.out.println("Course: " + batch.get().getCourseTitle());
    }

    @Test
    void testDatabaseConnection() 
    {
        long count = repository.count();
        assertTrue(count >= 0);
        System.out.println("Total Batches in Database: " + count);
    }

    @Test
    void testBatchDetails() 
    {
        List<SoftwareTrainingBatch> batches = repository.findAll();
        if (!batches.isEmpty()) 
        {
            SoftwareTrainingBatch batch = batches.get(0);
            assertNotNull(batch.getCourseTitle());
            assertNotNull(batch.getTrainerName());
            System.out.println("Course: " + batch.getCourseTitle());
            System.out.println("Trainer: " + batch.getTrainerName());
            System.out.println("Learners: " + batch.getLearnerCount());
        }
    }

    @Test
    void testTotalBatchCount() 
    {
        long count = repository.count();
        assertTrue(count >= 0);
        System.out.println("Total Batch Count: " + count);
    }
}