CREATE TABLE TB_Tags(
    id SERIAL PRIMARY KEY,
    tool_id INTEGER NOT NULL,
    description TEXT NOT NULL,
    CONSTRAINT fk_tools_01 FOREIGN KEY(tool_id) REFERENCES TB_Tools(id)
);