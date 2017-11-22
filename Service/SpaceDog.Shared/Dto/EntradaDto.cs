using SpaceDog.Shared.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SpaceDog.Shared.Dto
{
    class EntradaDto
    {
        public int Id { get; set; }
        
        public string Folio { get; set; }

        public DateTime Fecha { get; set; }
        public TimeSpan Hora { get; set; }
        
        public Turno? Turno { get; set; }

        public int UsuarioId { get; set; }
        public ICollection<Carga> Cargas { get; set; }

        public double TotalMacarela { get; set; }
        public double TotalJaponesa { get; set; }
        public double TotalMonterrey { get; set; }
        public double TotalRayadillo { get; set; }
        public double TotalBocona { get; set; }
        public double TotalAnchoveta { get; set; }
        public double TotalCrinuda { get; set; }

        public double PorcentajeMacarela { get; set; }
        public double PorcentajeJaponesa { get; set; }
        public double PorcentajeMonterrey { get; set; }
        public double PorcentajeRayadillo { get; set; }
        public double PorcentajeBocona { get; set; }
        public double PorcentajeAnchoveta { get; set; }
        public double PorcentajeCrinuda { get; set; }

        public double Totales { get; set; }

        public bool IsDeleted { get; set; }

    }
}
