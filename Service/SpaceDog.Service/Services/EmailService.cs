using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using SendGrid;
using SendGrid.Helpers.Mail;

namespace SpaceDog.Service.Services
{
    public class EmailService
    {

        private static readonly string _mailFrom = "";
        public string mailTo { get; set; }


        public static void SendPasswordRecoveryMail(string mailTo, string content)
        {
            SendEmail(mailTo, "Cambio de contraseña", content).GetAwaiter();
        }

        public static async Task SendEmail(string mailTo, string reason, string content)
        {
            var apiKey = "";
            var client = new SendGridClient(apiKey);
            var from = new EmailAddress(_mailFrom, "");
            var subject = "Tu contraseña ha cambiado";
            var to = new EmailAddress(mailTo, "");
            var plainTextContent = "";
            var htmlContent = content;
            var msg = MailHelper.CreateSingleEmail(from, to, subject, plainTextContent, htmlContent);
            var response = await client.SendEmailAsync(msg);
        }
    }
}