var tfConfig = {
  base_path: "TableFilter-master/dist/tablefilter/",
  alternate_rows: true,
  responsive: true,
  btn_reset: true,
  rows_counter: true,
  loader: true,
  status_bar: true,
  mark_active_columns: {
    highlight_column: true
  },
  highlight_keywords: true,
  no_results_message: true,
  col_0: "none", // Name
  col_1: "select", // Gender
  col_2: "select", // Zip
  col_3: "select", // English
  col_4: "select", // Language
  col_5: "none", // Phone
  col_6: "none", // Address
  col_7: "none", // Country
  col_8: "select", // Mon
  col_9: "select", // Tue
  col_10: "select", // Wed
  col_11: "select", // Thu
  col_12: "select", // Fri
  col_13: "select", // Sat
  col_14: "select", // Sun

  col_types: [
    "string", // Name
    "string", // Gender
    "number", // Zip
    "string", // English
    "string", // Language
    "string", // Phone
    "number", // Address
    "string", // Country
    "string", // Mon
    "string", // Tue
    "string", // Wed
    "string", // Thu
    "string", // Fri
    "string", // Sat
    "string" // Sun
  ],
  /**
   * Custom options definition of a per column basis, ie:
   *	custom_options: {
   *      cols:[0, 1],
   *      texts: [
   *          ['a0', 'b0', 'c0'],
   *          ['a1', 'b1', 'c1']
   *      ],
   *      values: [
   *          ['a0', 'b0', 'c0'],
   *          ['a1', 'b1', 'c1']
   *      ],
   *      sorts: [false, true]
   *  }
   */
  custom_options: {
    cols: [8, 9,10,11,12,13,14],
    texts: [
      ["Morning", "Noon", "Evening"],
      ["Morning", "Noon", "Evening"],
      ["Morning", "Noon", "Evening"],
      ["Morning", "Noon", "Evening"],
      ["Morning", "Noon", "Evening"],
      ["Morning", "Noon", "Evening"],
      ["Morning", "Noon", "Evening"],

    ],
    values: [
      [
          "*Morn",
          "*Noo",
          "*Even"
      ],
      [
        "*Morn",
        "*Noo",
        "*Even"
      ],
          [
            "*Morn",
            "*Noo",
            "*Even"
          ],
          [
            "*Morn",
            "*Noo",
            "*Even"
          ],
      [
        "*Morn",
        "*Noo",
        "*Even"
      ],
      [
        "*Morn",
        "*Noo",
        "*Even"
      ],
      [
        "*Morn",
        "*Noo",
        "*Even"
      ],
    ],
    sorts: [false, false]
  },

  col_widths: [],
  extensions: [
    {
      name: "sort"
    }
  ],

  /** Bootstrap integration */

  // aligns filter at cell bottom when Bootstrap is enabled
  filters_cell_tag: "th",

  // allows Bootstrap table styling
  themes: [
    {
      name: "transparent"
    }
  ]
};

var tf = new TableFilter(document.querySelector(".table"), tfConfig);
tf.init();
