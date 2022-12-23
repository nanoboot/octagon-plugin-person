CREATE TABLE "READING_DIARY" (
 "ID" TEXT,
 "NAME" TEXT NOT NULL UNIQUE,
 "PAGE_COUNT" INTEGER NOT NULL,
 "PAGES_READ" INTEGER NOT NULL,
 "SINCE" TEXT,
 "UNTIL" TEXT,
 "IN_PROGRESS" INTEGER,
 "THING_ID" TEXT UNIQUE,
 "READING_DIARY_TYPE" TEXT NOT NULL,
 PRIMARY KEY("ID")
);