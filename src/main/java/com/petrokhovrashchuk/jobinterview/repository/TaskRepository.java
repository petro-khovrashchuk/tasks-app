package com.petrokhovrashchuk.jobinterview.repository;

import com.petrokhovrashchuk.jobinterview.model.Task;
import java.util.HashMap;
import org.springframework.stereotype.Component;

@Component
public class TaskRepository extends HashMap<Long, Task> {

}
