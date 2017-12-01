using SpaceDog.Shared.Data;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace SpaceDog.Service.Services
{
    public class FolioService
    {

        public static string GenerateFolio()
        {
            return DateTime.Now.ToString("yyyyMMdd_HHmmss");
        }

    }
}