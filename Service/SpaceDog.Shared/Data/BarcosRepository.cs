using SpaceDog.Shared.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SpaceDog.Shared.Data
{
    public class BarcosRepository : BaseRepository<Barco>
    {
        public BarcosRepository(Context context) : base(context)
        {
        }

        public override Barco Get(int id, bool includeRelatedEntities = true)
        {
            var barco = Context.Barcos.AsQueryable();

            if (includeRelatedEntities)
            {
                barco = barco.Include(b => b.Usuario);
            }

            return barco
                .Where(b => b.Id == id)
                .SingleOrDefault();
        }

        public override IList<Barco> GetList()
        {
            return Context.Barcos
                .Include(b => b.Usuario)
                .OrderBy(b => b.Nombre)
                .ToList();
        }
    }
}
