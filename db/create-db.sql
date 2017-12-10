USE master;
GO

--Delete the TestData database if it exists.
IF EXISTS(SELECT * from sys.databases WHERE name='db')
BEGIN
    DROP DATABASE db;
END
GO

--Create a new database called TestData.
CREATE DATABASE db;
