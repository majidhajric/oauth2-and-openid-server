const withMT = require("@material-tailwind/html/utils/withMT");
/** @type {import('tailwindcss').Config} */
module.exports = withMT({
  content: ["./../web-application/src/main/**/*.{html,js}"],
  theme: {
    extend: {},
  },
  plugins: [],
});