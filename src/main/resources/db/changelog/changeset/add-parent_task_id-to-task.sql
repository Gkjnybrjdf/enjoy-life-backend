ALTER TABLE task ADD COLUMN parent_task_id int;
ALTER TABLE task
    ADD CONSTRAINT task_parent_task_id_fkey FOREIGN KEY (parent_task_id) REFERENCES task (id);