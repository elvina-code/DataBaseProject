$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("DBFeatures/CartItemTable.feature");
formatter.feature({
  "line": 2,
  "name": "Cart item tests",
  "description": "",
  "id": "cart-item-tests",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@regression"
    }
  ]
});
formatter.before({
  "duration": 1641328,
  "status": "passed"
});
formatter.scenario({
  "line": 4,
  "name": "Add new data to table cart_item",
  "description": "",
  "id": "cart-item-tests;add-new-data-to-table-cart-item",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 3,
      "name": "@cart_item"
    }
  ]
});
formatter.step({
  "line": 6,
  "name": "remove all records from \"cart_item\" table",
  "keyword": "Given "
});
formatter.step({
  "line": 8,
  "name": "add new data into the cart_items table",
  "rows": [
    {
      "cells": [
        "id",
        "quantity",
        "total_price",
        "food_id"
      ],
      "line": 9
    },
    {
      "cells": [
        "101",
        "324",
        "38.99",
        "23"
      ],
      "line": 10
    },
    {
      "cells": [
        "102",
        "543",
        "49.99",
        "24"
      ],
      "line": 11
    },
    {
      "cells": [
        "103",
        "548",
        "30.99",
        "25"
      ],
      "line": 12
    },
    {
      "cells": [
        "104",
        "734",
        "86.99",
        "26"
      ],
      "line": 13
    },
    {
      "cells": [
        "105",
        "999",
        "35.99",
        "27"
      ],
      "line": 14
    }
  ],
  "keyword": "When "
});
formatter.step({
  "line": 16,
  "name": "verify that new data has been inserted into cart_item",
  "rows": [
    {
      "cells": [
        "id",
        "quantity",
        "total_price",
        "food_id"
      ],
      "line": 17
    },
    {
      "cells": [
        "101",
        "324",
        "38.99",
        "23"
      ],
      "line": 18
    },
    {
      "cells": [
        "102",
        "543",
        "49.99",
        "24"
      ],
      "line": 19
    },
    {
      "cells": [
        "103",
        "548",
        "30.99",
        "25"
      ],
      "line": 20
    },
    {
      "cells": [
        "104",
        "734",
        "86.99",
        "26"
      ],
      "line": 21
    },
    {
      "cells": [
        "105",
        "999",
        "35.99",
        "27"
      ],
      "line": 22
    }
  ],
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "cart_item",
      "offset": 25
    }
  ],
  "location": "DataBaseValidationSteps.remove_all_records_from_cart_item_table(String)"
});
formatter.result({
  "duration": 124624360,
  "error_message": "java.lang.NullPointerException\n\tat com.devxschool.utils.db.DataBaseUtils.executeQuery(DataBaseUtils.java:59)\n\tat com.devxschool.steps.DataBaseValidationSteps.remove_all_records_from_cart_item_table(DataBaseValidationSteps.java:36)\n\tat ✽.Given remove all records from \"cart_item\" table(DBFeatures/CartItemTable.feature:6)\n",
  "status": "failed"
});
formatter.match({
  "location": "DataBaseValidationSteps.add_new_data_into_the_cart_items_table(CartItems\u003e)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "DataBaseValidationSteps.verify_that_new_data_has_been_inserted(CartItems\u003e)"
});
formatter.result({
  "status": "skipped"
});
formatter.after({
  "duration": 39552,
  "status": "passed"
});
});