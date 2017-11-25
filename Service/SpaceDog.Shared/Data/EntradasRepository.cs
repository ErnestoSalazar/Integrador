using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using SpaceDog.Shared.Dto;
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
                entrada = entrada.Include(e => e.Usuario)
                    .Include(e => e.Cargas);
            }

            return entrada
                .Where(e => e.Id == id && e.IsDeleted != true)
                .SingleOrDefault();
        }

        public override IList<Entrada> GetList()
        {
            return Context.Entradas
                .Include(e => e.Usuario)
                .Include(e => e.Cargas)
                .Where(e => e.IsDeleted != true)
                .ToList();
        }

        public void DeleteD(int id)
        {
            var entrada = Context.Entradas.Find(id);
            entrada.IsDeleted = true;
            Context.Entry(entrada).State = EntityState.Modified;
            Context.SaveChanges();
        }

        public ReporteDto GetReporte(int id)
        {
            var entrada = Context.Entradas
                .Select(en => new ReporteDto
                {
                    Id = en.Id,
                    Folio = en.Folio,
                    Fecha = en.Fecha,
                    Hora = en.Hora,
                    Turno = en.Turno,
                    UsuarioId = en.UsuarioId,
                    Usuario = en.Usuario,
                    Cargas = en.Cargas.ToList(),
                    IsDeleted = en.IsDeleted
                })
                .Where(e => e.Id == id && e.IsDeleted != true) //e.Cargas.Any(c => c.Especie.ToString() == especie) &&
                .SingleOrDefault();

            if (entrada != null  && entrada.Cargas.Count > 0)
            {
                var cargasMacarela = entrada.Cargas.Where(c => c.Especie == Especie.Macarela).ToList();
                var cargasJaponesa = entrada.Cargas.Where(c => c.Especie == Especie.Japonesa).ToList();
                var cargasMonterrey = entrada.Cargas.Where(c => c.Especie == Especie.Monterrey).ToList();
                var cargasRayadillo = entrada.Cargas.Where(c => c.Especie == Especie.Rayadillo).ToList();
                var cargasBocona = entrada.Cargas.Where(c => c.Especie == Especie.Bocona).ToList();
                var cargasAnchoveta = entrada.Cargas.Where(c => c.Especie == Especie.Anchoveta).ToList();
                var cargasCrinuda = entrada.Cargas.Where(c => c.Especie == Especie.Crinuda).ToList();


                if (cargasMacarela.Count > 0) { entrada.TotalMacarela = GetTotalPesaje(cargasMacarela); }
                if (cargasJaponesa.Count > 0) { entrada.TotalJaponesa = GetTotalPesaje(cargasJaponesa); }
                if (cargasMonterrey.Count > 0) { entrada.TotalMonterrey = GetTotalPesaje(cargasMonterrey); }
                if (cargasRayadillo.Count > 0) { entrada.TotalRayadillo = GetTotalPesaje(cargasRayadillo); }
                if (cargasBocona.Count > 0) { entrada.TotalBocona = GetTotalPesaje(cargasBocona); }
                if (cargasAnchoveta.Count > 0) { entrada.TotalAnchoveta = GetTotalPesaje(cargasAnchoveta); }
                if (cargasCrinuda.Count > 0) { entrada.TotalCrinuda = GetTotalPesaje(cargasCrinuda); }

                entrada.Totales =
                    entrada.TotalMacarela +
                    entrada.TotalJaponesa +
                    entrada.TotalMonterrey +
                    entrada.TotalRayadillo +
                    entrada.TotalBocona +
                    entrada.TotalAnchoveta +
                    entrada.TotalCrinuda;

                entrada.PorcentajeMacarela = (entrada.TotalMacarela * 100) / entrada.Totales;
                entrada.PorcentajeJaponesa = (entrada.TotalJaponesa * 100) / entrada.Totales;
                entrada.PorcentajeMonterrey = (entrada.TotalMonterrey * 100) / entrada.Totales;
                entrada.PorcentajeRayadillo = (entrada.TotalRayadillo * 100) / entrada.Totales;
                entrada.PorcentajeBocona = (entrada.TotalBocona * 100) / entrada.Totales;
                entrada.PorcentajeAnchoveta = (entrada.TotalAnchoveta * 100) / entrada.Totales;
                entrada.PorcentajeCrinuda = (entrada.TotalCrinuda * 100) / entrada.Totales;
            }

            return entrada;
        }

        public List<EntradaDto> GetListByDate(DateTime dateInicio, DateTime dateFin)
        {
            var entradas = Context.Entradas
                .Select(en => new EntradaDto
                {
                    Id = en.Id,
                    Folio = en.Folio,
                    Fecha = en.Fecha,
                    Hora = en.Hora,
                    Turno = en.Turno,
                    UsuarioId = en.UsuarioId,
                    Usuario = en.Usuario,
                    Cargas = en.Cargas.ToList(),
                    IsDeleted = en.IsDeleted
                })
                .Where(en => en.Fecha >= dateInicio && en.Fecha <= dateFin && en.IsDeleted != true) //e.Cargas.Any(c => c.Especie.ToString() == especie) &&
                .ToList();

            return entradas;
        }


        public Entrada GetEntradasByFolio(string folio)
        {
            return Context.Entradas
                .Where(e => e.Folio == folio)
                .SingleOrDefault();
        }

        

        public List<Carga> GetListOfCargasInEntrada(ICollection<Carga> cargas)
        {
            List<Carga> _cargas = new List<Carga>();
            foreach(var carga in cargas)
            {
                Context.Cargas.Attach(carga);
                _cargas.Add(carga);
            }

            return _cargas.ToList();

        }


        public List<Carga> GetListOfCargasInEntrada(List<int> CargasId)
        {
            List<Carga> _cargas = new List<Carga>();
            Carga carga;
            foreach(var cargaId in CargasId)
            {
                carga = Context.Cargas.Find(cargaId);
                _cargas.Add(carga);
            }
            return _cargas;
        }


        public void InsertListInContext(Entrada entrada)
        {
            List<Carga> cargas = new List<Carga>();
            foreach(var carga in entrada.Cargas)
            {
                Context.Cargas.Attach(carga);
                Context.Entry(carga).State = EntityState.Modified;
                cargas.Add(carga);
            }
            entrada.Cargas = cargas;
            Context.Entradas.Add(entrada);
        }


        private double GetTotalPesaje(List<Carga> cargas)
        {
            return cargas.Sum(c => c.Cantidad);
        }

    }
}
