using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace SpaceDog.Service.Services
{
    public class DateTimeService
    {


        private static DateTime GetActualTime()
        {
            TimeZoneInfo timeZoneInfo;
            DateTime dateTime;

            timeZoneInfo = TimeZoneInfo.FindSystemTimeZoneById("US Mountain Standard Time");
            dateTime = TimeZoneInfo.ConvertTime(DateTime.Now, timeZoneInfo);

            return dateTime;
        }

        public static string GetDateNowShortString()
        {
            return GetActualTime().ToShortDateString();
        }

        public static TimeSpan GetTimeNow()
        {
            DateTime dateTime = GetActualTime();
            return TimeSpan.Parse($"{dateTime.Hour}:{dateTime.Minute}");
        }

    }
}