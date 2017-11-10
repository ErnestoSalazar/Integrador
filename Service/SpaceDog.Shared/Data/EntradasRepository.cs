using SpaceDog.Shared.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SpaceDog.Shared.Data
{
    public class EntradasRepository : BaseRepository<Entrada>
    {
        public EntradasRepository(Context context) : base(context)
        {
        }

        public override Entrada Get(int id, bool includeRelatedEntities = true)
        {
            var entrada = Context.Entradas.AsQueryable();
            if (includeRelatedEntities)
            {
                entrada.Include(e => e.Usuario);
            }

            return entrada
                .Where(e => e.Id == id)
                .SingleOrDefault();
        }

        public override IList<Entrada> GetList()
        {
            return Context.Entradas
                .Include(e => e.Usuario)
                .ToList();
        }



    }
}
