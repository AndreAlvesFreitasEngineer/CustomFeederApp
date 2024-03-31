import json
import random
from datetime import datetime, timedelta
from geopy.distance import geodesic

PLACES = {
"New York City":(40.7128,-74.0060),
"Times Square":(40.7580,-73.9855),
"Empire State Building":(40.748817,-73.985428),
"Central Park":(40.7851,-73.9683),
"Brooklyn Bridge":(40.7061,-73.9969),
"Statue of Liberty":(40.6892,-74.0445),
"Grand Central Terminal":(40.7527,-73.9772),
"One World Trade Center":(40.7126,-74.0133),
"Rockefeller Center":(40.7587,-73.9787),
"The Metropolitan Museum of Art":(40.7794,-73.9632),
"Broadway":(40.7590,-73.9845),
"Madison Square Garden":(40.7505,-73.9934),
"Brooklyn Botanic Garden":(40.6694,-73.9626),
"Coney Island":(40.5749,-73.9851),
"High Line":(40.7479,-74.0048),
"Yankee Stadium":(40.8296,-73.9262),
"The Cloisters":(40.8648,-73.9316),
"Wall Street":(40.7074,-74.0119),
"Central Park Zoo":(40.7678,-73.9718),
"Fifth Avenue":(40.7812,-73.9665),
# Add more real places as needed
}

RATE_PER_KM=2.5

def generate_random_date(): 
    start_date = datetime(2024, 1, 1) 
    end_date = datetime(2024, 12, 31) 
    return start_date + timedelta(days=random.randint(0, (end_date - start_date).days))

def calculate_distance(location1, location2): 
    return geodesic(location1, location2).kilometers

def calculate_price(start_location, end_location): 
    distance = calculate_distance((start_location["latitude"], start_location["longitude"]), 
                                  (end_location["latitude"], end_location["longitude"])) 
    return distance * RATE_PER_KM

def generate_entry():
    start_place = random.choice(list(PLACES.keys()))
    end_place = random.choice(list(PLACES.keys()))
    important_places = random.sample(list(PLACES.keys()), 3)
    start_date = generate_random_date().strftime("%Y-%m-%d")
    end_date = generate_random_date().strftime("%Y-%m-%d")

    start_location = {"latitude": PLACES[start_place][0], "longitude": PLACES[start_place][1], "place": start_place}
    end_location = {"latitude": PLACES[end_place][0], "longitude": PLACES[end_place][1], "place": end_place}
    important_locations = [{"latitude": PLACES[place][0], "longitude": PLACES[place][1], "place": place} for place in important_places]

    price = calculate_price(start_location, end_location)

    return {
        "start": start_location,
        "end": end_location,
        "important_places": important_locations,
        "start_date": start_date,
        "end_date": end_date,
        "price": price
    }

entries = [generate_entry() for _ in range(10000)]

with open("taxi_records.json", "w") as f:
    json.dump(entries, f, indent=2)