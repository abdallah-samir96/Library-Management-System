ALTER TABLE book
     ADD CONSTRAINT
     book_created_by_constraint FOREIGN KEY (created_by) REFERENCES user_info(email);

ALTER TABLE book
     ADD CONSTRAINT
     book_updated_by_constraint FOREIGN KEY (updated_by) REFERENCES user_info(email);