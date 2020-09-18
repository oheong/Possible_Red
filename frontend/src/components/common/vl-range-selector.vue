<template>
  <vl-calendar
    @input="(date) => emitDate(date)"
    :is-selected="isSelected"
    :is-disabled="calculateDisabled"
    :custom-classes="customClasses"
    :show-weeks-number="showWeeksNumber"
    :default-date="defaultDate"
    :single-month="singleMonth"
    :first-day-of-week="firstDayOfWeek"
    ref="calendar"
  />
</template>

<script>
import VlCalendar from "./vl-calendar";
import { DAYS_SHORTCUTS } from "../cal_utils/days";
import Moment from "moment";

export default {
  name: "VlRangeSelector",
  components: {
    VlCalendar,
  },
  data() {
    return { now: new Date() };
  },
  props: {
    startDate: String,
    endDate: String,
    customClasses: Object,
    showWeeksNumber: Boolean,
    defaultDate: String,
    blockStartDate: Boolean,
    disabled: Boolean,
    isDisabled: Function,
    singleMonth: Boolean,
    enableSingleDate: Boolean,
    firstDayOfWeek: {
      type: String,
      validator: (v) => DAYS_SHORTCUTS.includes(v),
      default: "mon",
    },
  },

  methods: {
    change_mon(month) {
      switch (month) {
        case "Jan":
          return "01";
        case "Feb":
          return "02";
        case "Mar":
          return "03";
        case "Apr":
          return "04";
        case "May":
          return "05";
        case "Jun":
          return "06";
        case "Jul":
          return "07";
        case "Aug":
          return "08";
        case "Sep":
          return "09";
        case "Oct":
          return "10";
        case "Nov":
          return "11";
        case "Dec":
          return "12";
      }
    },
    format_date(value) {
      if (value) {
        //console.log(value);
        let strValue = String(value);

        let month = strValue.substring(4, 7);
        //console.log(this.change_mon(month));

        let year = strValue.substring(11, 15);
        //console.log(year);

        let day = strValue.substring(8, 10);
        //console.log(day);

        return year + "-" + this.change_mon(month) + "-" + day;
      }
    },
    emitDate(date) {
      if (
        this.blockStartDate ||
        (this.startDate && !this.endDate && date >= this.startDate)
      ) {
        this.$emit("update:endDate", date);
      } else {
        this.$emit("update:startDate", date);
        if (this.endDate) {
          this.$emit("update:endDate", null);
        }
      }
      this.$emit("focus");
    },

    isSelected(date) {
      //console.log(this.startDate);
      //console.log(this.format_date(this.now));

      let today = new Date(this.format_date(this.now));
      let start_day = new Date(this.startDate);

      if (start_day < today) return false;

      if (!this.startDate && !this.endDate) {
        return false;
      } else if (!this.endDate) {
        return this.startDate === date;
      } else {
        return date >= this.startDate && date <= this.endDate;
      }
    },

    calculateDisabled(date) {
      const isDisabled = this.isDisabled || (() => false);

      if (this.disabled) {
        return true;
      } else if (this.startDate && !this.endDate) {
        return (
          isDisabled(date) ||
          (!this.enableSingleDate && date === this.startDate)
        );
      } else {
        return isDisabled(date);
      }
    },
  },
};
</script>
