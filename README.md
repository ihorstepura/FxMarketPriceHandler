# FxMarketPriceHandler

### Market Price Handler

The purpose of application is to return the latest price for an instrument whenever it is asked for
the next currency pairs:

- EUR/USD,
- GBP/USD,
- EUR/JPY

Only such pairs are supported in current version.

Current application uses mocked prices data from marketPriceFeed.csv file.

It is possible to use external data source in future e.g. some message broker. To do so should be used `void
onMessage(String message)` in `PriceListener`.

To get the latest price you should provide one of supported pairs, and you will get it.

To test the application locally can be used `http://localhost:8080/api/v1/price?priceFeed=EUR/JPY` path.
In case if resource does not exist you will get `404 Not Found`.