using SpaceDog.Shared.Data;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SpaceDog.Shared
{
    class Program
    {
        static void Main(string[] args)
        {

            using (var context = new Context())
            {
                var userId = 1;

                var user = context.Usuarios.SingleOrDefault(u => u.Id == userId);

                Console.WriteLine(user.Nombre);
            }

            Console.ReadLine();

        }
    }
}
