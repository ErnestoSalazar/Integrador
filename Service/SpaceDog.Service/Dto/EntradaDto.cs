﻿using SpaceDog.Service.Services;
using SpaceDog.Shared.Models;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace SpaceDog.Service.Dto
{

    public class EntradaDto
    {

        public int Id { get; set; }
        [Required]
        public string Folio { get; set; }
        
        public String Fecha { get; set; }
        public TimeSpan Hora { get; set; }

        [Required]
        public Turno? Turno { get; set; }

        public int? UsuarioId { get; set; }
        public ICollection<Carga> Cargas { get; set; }



        public List<int> CargasId { get; set; }


        public Entrada ToModel()
        {
            return new Entrada()
            {
                Id          =  Id,
                Folio       =  FolioService.GenerateFolio(),
                Fecha       =  DateTime.Parse(DateTimeService.GetDateNowShortString()),
                Hora        =  DateTimeService.GetTimeNow(),
                Turno       =  Turno.Value,
                UsuarioId   =  UsuarioId.Value,
                Cargas      =  Cargas
            };
        }

    }
}