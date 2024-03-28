DROP DATABASE if EXISTS oopDatabase;
CREATE DATABASE oopDatabase;
USE oopDatabase;

DROP TABLE IF EXISTS Users, Events, Movie, SportsEvent, Concert, Tickets;

CREATE TABLE Users (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(255) NOT NULL,
    UserType VARCHAR(50) NOT NULL,
    Email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE Events (
    EventID INT AUTO_INCREMENT PRIMARY KEY,
    EventName VARCHAR(255) NOT NULL,
    Venue VARCHAR(255) NOT NULL,
    EventDateTime DATETIME NOT NULL, -- Stores both date and time
    TicketPrice DOUBLE,
    CancellationFee DOUBLE
);

CREATE TABLE Movie (
    eventID BIGINT PRIMARY KEY REFERENCES Events(EventID),
    mainCast TEXT,
    rating VARCHAR(255),
    duration INTEGER
);

CREATE TABLE SportsEvent (
    eventID BIGINT PRIMARY KEY REFERENCES Events(EventID),
    participants TEXT,
    sport VARCHAR(255)
);

CREATE TABLE Concert (
    eventID BIGINT PRIMARY KEY REFERENCES Events(EventID),
    performers TEXT
);

CREATE TABLE Tickets (
    TicketID  INT AUTO_INCREMENT PRIMARY KEY,
    CustomerID INT,
    EventID INT,
    BookingDate DATE,
    FOREIGN KEY (CustomerID) REFERENCES Users(UserID),
    FOREIGN KEY (EventID) REFERENCES Events(EventID)
);

INSERT INTO Users (Username, UserType, Email) VALUES
('AliceJones', 'Customer', 'alice.jones@example.com'),
('BobSmith', 'Customer', 'bob.smith@example.com'),
('CharlieAdmin', 'Admin', 'charlie.admin@example.com');

INSERT INTO Events (EventName, Venue, EventDateTime, TicketPrice, CancellationFee) VALUES
('Tech Expo 2024', 'Convention Center', '2024-04-15 09:00:00', 99.99, 20.00),
('Live Music Fest', 'Outdoor Stadium', '2024-06-20 18:00:00', 149.99, 25.00),
('Cochella', 'Dessert', '2024-04-05 19:00:00', 15.0, 20.0),
('TomorrowLand', 'Stadium', '2024-04-10 20:00:00', 20.0, 25.0);

INSERT INTO Movie (eventID, mainCast, rating, duration) VALUES 
(2, '{"Cillian", "Florence"}', 'PG', 150),
(3, '{"Tom", "Zendaya", "Peter"}', 'PG-13', 120);

INSERT INTO SportsEvent (eventID, participants, sport) VALUES 
(4, '{"Lakers", "Boston Celtics"}', 'Basketball'),
(5, '{"Liverpool", "Man U"}', 'Soccer');

INSERT INTO Concert (eventID, performers) VALUES 
(6, '{"Bruno", "Taylor"}'),
(7, '{"Coldplay", "Wave to Earth", "Men I Trust"}');

INSERT INTO Tickets (CustomerID, EventID, BookingDate) VALUES
(1, 1, '2024-03-01'),
(2, 1, '2024-03-02'),
(1, 2, '2024-03-05'),
(2, 2, '2024-03-05');