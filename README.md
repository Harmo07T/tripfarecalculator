LITTLEPAY

####The problem####

Given an input file in CSV format containing a single tap on or tap off per line you will need to create an output
file containing trips made by customers.
taps.csv [input file]
You are welcome to assume that the input file is well formed and is not missing data.

Example input file:
ID, DateTimeUTC, TapType, StopId, CompanyId, BusID, PAN
1, 22-01-2018 13:00:00, ON, Stop1, Company1, Bus37, 5500005555555559
2, 22-01-2018 13:05:00, OFF, Stop2, Company1, Bus37, 5500005555555559
trips.csv [output file]

You will need to match the tap ons and tap offs to create trips. You will need to determine how much to
charge for the trip based on whether it was complete, incomplete or cancelled and where the tap on and tap
off occurred. You will need to write the trips out to a file called trips.csv.

Example output file:
Started, Finished, DurationSecs, FromStopId, ToStopId, ChargeAmount, CompanyId, BusID, PAN,
Status
22-01-2018 13:00:00, 22-01-2018 13:05:00, 900, Stop1, Stop2, $3.25, Company1, B37,
5500005555555559, COMPLETED

####To build the project####
mvn clean install will generate /target/tripfarecalculator-0.0.1-SNAPSHOT.jar
run in mcd: java -jar tripfarecalculator-0.0.1-SNAPSHOT.jar

The outputfile will be in src/main/resources/trips.csv

####Assumptions####
A customer can't TAPOFF if there's no TAPON
No bus mixup
