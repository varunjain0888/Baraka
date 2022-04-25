# Baraka
# UI Structure / Project Structure
1. Concat Adapter is used to bind multiple types of views in recyclerview like horizontal and vertical using different adapters
2. Material components are used for building UI components.
3. Uses MVVM Architecture with repository pattern 

# SECTION 1:

# Stock Ticker And News Feed Application Functional Structure
1. Stocks read from csv file using jackson and mappepd into object class having two parameter i.e name and price
2. After reading from csv it is converted into flow and stored into room database 
3. Live data is subscribed for fetching stocks from room database. 
4. There is delay added to onEach block so as it will continiously update prices for stocks after every 1 sec
5. Any update in DB will trigger update in UI as it observed as live data

# SECTION 2:
# Top Trending News/First 6 news articles Horizontal list
1. News Read from json file using jackson
2. After fetching news from json file it is stored in Database 
3. Live data is observed for any changes 
4. Top 6 is only passed to top trending news adapter

# SECTION 3:
# Vertical list for news feeds
1. Above fetched news from json file which is there in database is fetched and drop first 6 element 
2. Remaining data passed to news feed adapter to display on UI
