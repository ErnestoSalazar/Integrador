using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using SendGrid;
using SendGrid.Helpers.Mail;
using SpaceDog.Shared;

namespace SpaceDog.Service.Services
{
    public class EmailService
    {

        public string mailTo { get; set; }


        public static void SendPasswordRecoveryMail(string mailTo, string content)
        {
            SendEmail(mailTo, Strings.CAMBIO_DE_CONTRASENA, content).GetAwaiter();
        }

        public static void SendPasswordForNewUser(string mailTo, string content)
        {
            SendEmail(mailTo, Strings.BIENVENIDO, content).GetAwaiter();
        }

        private static async Task SendEmail(string mailTo, string reason, string content)
        {
            var client = new SendGridClient(Strings.SENDGRID_KEY);
            var from = new EmailAddress(Strings.MAIL_FROM, Strings.COMPANY);
            var subject = reason;
            var to = new EmailAddress(mailTo, "");
            var plainTextContent = "";
            var htmlContent = content;
            var msg = MailHelper.CreateSingleEmail(from, to, subject, plainTextContent, htmlContent);
            var response = await client.SendEmailAsync(msg);
        }
    }
}