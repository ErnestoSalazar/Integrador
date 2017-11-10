[assembly: WebActivator.PostApplicationStartMethod(typeof(SpaceDog.Service.App_Start.SimpleInjectorWebApiInitializer), "Initialize")]

namespace SpaceDog.Service.App_Start
{
    using System.Web.Http;
    using SimpleInjector;
    using SimpleInjector.Integration.WebApi;
    using SpaceDog.Shared.Data;

    public static class SimpleInjectorWebApiInitializer
    {
        /// <summary>Initialize the container and register it as Web API Dependency Resolver.</summary>
        public static void Initialize()
        {
            var container = new Container();
            container.Options.DefaultScopedLifestyle = new WebApiRequestLifestyle();
            
            InitializeContainer(container);

            container.RegisterWebApiControllers(GlobalConfiguration.Configuration);
       
            container.Verify();
            
            GlobalConfiguration.Configuration.DependencyResolver =
                new SimpleInjectorWebApiDependencyResolver(container);
        }
     
        private static void InitializeContainer(Container container)
        {
            // Register your services here (remove this line).

            // For instance:
            // container.Register<IUserRepository, SqlUserRepository>(Lifestyle.Scoped);
            container.Register<Context>(Lifestyle.Scoped);
            container.Register<UsuariosRepository>(Lifestyle.Scoped);
            container.Register<BarcosRepository>(Lifestyle.Scoped);
            container.Register<CargasRepository>(Lifestyle.Scoped);
        }
    }
}