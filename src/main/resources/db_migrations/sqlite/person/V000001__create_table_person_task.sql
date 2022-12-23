CREATE TABLE "PERSON_TASK" (
 "ID" TEXT,
 "NAME" TEXT NOT NULL UNIQUE,
 "GROUP" TEXT,
 "SINCE" TEXT,
 "DEADLINE" TEXT,
 "STATUS" TEXT,
 "RESOLUTION" TEXT,
 "RESOLUTION_COMMENT" TEXT,
 "SORTKEY" INTEGER,
 "IMPORTANCE" TEXT,
 "SIZE" TEXT,
 "NOTE" TEXT,
 "OBJECT" TEXT,
 "REMINDER_TYPE" TEXT,
 "TODO" TEXT,
 "ASAP" INTEGER,
 "REPEAT_EVERY_X_DAYS" INTEGER,
 "PROGRESS_ESTIMATION" INTEGER,
 "PARENT_ID" TEXT,
 FOREIGN KEY("PARENT_ID") REFERENCES "PERSON_TASK"("ID"),
 PRIMARY KEY("ID")
);
