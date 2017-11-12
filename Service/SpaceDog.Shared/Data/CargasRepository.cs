using SpaceDog.Shared.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SpaceDog.Shared.Data
{
    public class CargasRepository : BaseRepository<Carga>
    {

        public CargasRepository(Context context) : base(context)
        {
        }

        public override Carga Get(int id, bool includeRelatedEntities = true)
        {
            var carga = Context.Cargas.AsQueryable();

            if (includeRelatedEntities)
            {
                carga.Include(c => c.Barco);
            }

            return carga
                .Where(c => c.Id == id)
                .SingleOrDefault();
            
        }

        public override IList<Carga> GetList()
        {
            return Context.Cargas
                .Include(c => c.Barco)
                .Include(c => c.Barco.Usuario)
                .ToList();
        }

        public IList<Carga> GetListOfCargasOfBarco(int BarcoId)
        {
            return Context.Cargas
                .Where(c => c.BarcoId == BarcoId)
                .ToList();
        }

    }
}
